package com.kakaopay.showmoney.provider.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.dto.ResponseDTO;
import com.kakaopay.showmoney.data.dto.SprinkleDTO;
import com.kakaopay.showmoney.data.model.AcceptMoney;
import com.kakaopay.showmoney.data.model.Sprinkle;
import com.kakaopay.showmoney.provider.SendProvider;
import com.kakaopay.showmoney.service.AcceptingService;
import com.kakaopay.showmoney.service.SendingService;
import com.kakaopay.showmoney.service.TokenGeneratorService;
import com.kakaopay.showmoney.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 토큰 발급을 위한 Provider
 * @author seon
 */
@Slf4j
@Component("sendProvider")
public class SendProviderImpl implements SendProvider {	
	@Autowired
	SendingService sendingService;
	
	@Autowired
	AcceptingService acceptingService;
	
	@Autowired
	TokenGeneratorService tokenGen;
	
	@Autowired
	CommonUtils common;

	/**
	 * 돈 뿌리기
	 */
	@Override
	public ResponseDTO<Map<String, Object>> doSprinkle(ParameterDTO param) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		StringBuffer token = tokenGen.generateTokens(3);		// 3자리 토큰생성
		boolean tokenChk = tokenGen.isExistTokens(token.toString());
		Sprinkle sprinkle = new Sprinkle();
		
		// 토큰 중복 체크
		if(tokenChk) {
			log.debug("------- 토큰값 중복발생! -----");
			
			do {
				log.debug("========== 토큰값 재생성! ==========");
				token.setLength(0);;
				token = tokenGen.generateTokens(3);		// 3자리 토큰생성
				tokenChk = tokenGen.isExistTokens(token.toString());
			} while (tokenChk!=true);
		}
		
		log.debug("### 생성된 토큰값 ::: "+token.toString());		
		
		// 값 세팅
		sprinkle
			.setSprinkleId("SP"+common.getNowTimeStamp("yyyyMMddHHmmss")+param.getUserId())	// SP(현재시각)(유저ID)
			.setCreateId(param.getUserId())
			.setRoomId(param.getRoomId())
			.setMoney(param.getMoney())
			.setRemainMoney(param.getMoney())
			.setTokens(token.toString())
			.setCreateDt(common.getNowDate())
			.setExpiryDt(common.getSomeMinuteAfter(10))
			.setDividedMoney(common.distributionMoney(param.getMoney(), param.getCount()))
			.setMoneyOrd(0);
		
		log.debug("[DTO 데이터 확인] "+sprinkle.toString());
		
		// 토큰 DB에 적재 및 뿌리기 지갑 생성
		try {
			sendingService.sprinkle(sprinkle);
		} catch (Exception e) {
			resultMap.put("result", ResponseDTO.ERROR("지갑 생성에 실패했습니다. 요청 값을 다시 확인해주세요"));
			return ResponseDTO.ERROR("지갑 생성에 실패했습니다. 요청 값을 다시 확인해주세요");
		}
		
		resultMap.put("result", ResponseDTO.OK(sprinkle));

		return ResponseDTO.OK(resultMap);
	}

	/**
	 * 뿌리기 지갑 조회
	 */
	@Override
	public ResponseDTO<Map<String,Object>> selectSprinkle(ParameterDTO param) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Sprinkle sprinkle = new Sprinkle();
		SprinkleDTO sprinkleDTO = new SprinkleDTO();
		
		try {
			sprinkle = sendingService.selectByParameter(param,"sprinkle");
			List<AcceptMoney> list = acceptingService.selectHistoryByParameter(param);
			if(sprinkle!=null) {
				log.debug("#### 뿌리기 지갑 데이터 확인 : "+sprinkle.toString());
				
				sprinkleDTO
					.setTokens(sprinkle.getTokens())
					.setRoomId(sprinkle.getRoomId())
					.setCreateDt(common.transFormStringToDate(sprinkle.getCreateDt()))
					.setMoney(sprinkle.getRemainMoney())
					.setAcceptList(list);
				
				resultMap.put("result", sprinkleDTO);
				
				return ResponseDTO.OK(resultMap);
			} else {
				return ResponseDTO.ERROR("뿌리기 내용이 존재하지 않습니다.");
			}
		} catch (Exception e) {
			return ResponseDTO.ERROR("뿌리기 지갑을 조회하던 중 에러가 발생했습니다.");
		}
	}
}