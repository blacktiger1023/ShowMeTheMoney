package com.kakaopay.showmoney.provider.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kakaopay.showmoney.data.dto.AcceptMoneyDTO;
import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.dto.ResponseDTO;
import com.kakaopay.showmoney.data.model.AcceptMoney;
import com.kakaopay.showmoney.data.model.Sprinkle;
import com.kakaopay.showmoney.provider.AcceptProvider;
import com.kakaopay.showmoney.service.AcceptingService;
import com.kakaopay.showmoney.service.SendingService;
import com.kakaopay.showmoney.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("acceptProvider")
public class AcceptProviderImpl implements AcceptProvider{

	@Autowired
	AcceptingService acceptingService;
	
	@Autowired
	SendingService sendingService;
	
	@Autowired
	CommonUtils common;
	
	@Override
	public ResponseDTO<AcceptMoneyDTO> takeMoney(ParameterDTO params) {
		AcceptMoneyDTO acceptDto = new AcceptMoneyDTO();
		AcceptMoney accept = new AcceptMoney();
		
		try {
			// 뿌리기 지갑에서 정보 가져오기
			Sprinkle sprinkle = sendingService.selectByParameter(params,"accept");
			
			// 만료시간
			Date expiredDt = common.transFormStringToDate(sprinkle.getExpiryDt());
			
			// 현재 시간보다 만료 시간이 더 큰지 확인
			if(expiredDt.compareTo(new Date())<0) { // 1. 만료시간 경과했는지 판단
				return ResponseDTO.ERROR("만료시간이 경과하여 금액을 받을 수 없습니다.");
			} else if(!params.getRoomId().equals(sprinkle.getRoomId())){	// 2. 대화방 속해있는지 여부 판단
				return ResponseDTO.ERROR("현재 해당 대화방에 속해있지 않습니다.");
			} else if(params.getUserId()==sprinkle.getCreateId()) {			// 3. 뿌리기 생성자 본인 여부 판단
				return ResponseDTO.ERROR("자신이 뿌린 돈은 받을 수 없습니다.");
			} else if(acceptingService.selectHistory(params)>0) {			// 4. 해당 뿌리기에 이미 수금한 이력이 존재하는지 판단
				return ResponseDTO.ERROR("이미 수금하셨습니다. 수금은 한 번만 가능합니다.");
			}
			
			String[] moneyArray = sprinkle.getDividedMoney().split(",");
			int myMoney = Integer.parseInt(moneyArray[sprinkle.getMoneyOrd()]);		// 5. 자신의 순서에 해당하는 배당금을 꺼내옴
			
			// 세팅
			accept
				.setEventId("EV"+common.getNowTimeStamp("yyyyMMddHHmmss")+params.getUserId())
				.setSprinkleId(sprinkle.getSprinkleId())
				.setAcceptId(params.getUserId())
				.setRoomId(params.getRoomId())
				.setTakeMoney(myMoney)
				.setEventDt(common.getNowDate())
				.setTokens(params.getTokens());
		
			acceptingService.insetTakeMoney(accept);			// 이력 생성
			sendingService.acceptSprinkle(params, accept.getTakeMoney());		// 수금완료한 돈 뺴기
			
			// 결과 값 세팅
			acceptDto
					.setRoomId(params.getRoomId())
					.setUserId(params.getUserId())
					.setAcceptMoney(accept.getTakeMoney())
					.setTokens(params.getTokens());
			
		} catch (Exception e) {
			log.error("수금을 하던 도중 알 수 없는 에러 발생");
			e.getStackTrace();
			e.getMessage();
			return ResponseDTO.ERROR("수금을 하던 도중 알 수 없는 에러 발생");
		}
		
		return ResponseDTO.OK(acceptDto);
	}
}
