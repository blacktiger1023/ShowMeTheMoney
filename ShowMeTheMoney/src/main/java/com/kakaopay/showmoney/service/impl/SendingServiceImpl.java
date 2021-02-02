package com.kakaopay.showmoney.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.mappers.SprinkleMapper;
import com.kakaopay.showmoney.data.model.Sprinkle;
import com.kakaopay.showmoney.service.SendingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("sendingService")
public class SendingServiceImpl implements SendingService {

	@Autowired
	SprinkleMapper sprinkleMapper;
	
	@Override
	public void sprinkle(Sprinkle sprinkle) throws Exception {
		log.debug("### 뿌리기 지갑 생성 시도...\n"+sprinkle.toString());
		
		try {
			sprinkleMapper.insertSprinkle(sprinkle);
		} catch (Exception e) {
			log.error("### 뿌리기 지갑 생성 도중 에러 발생");
			e.getStackTrace();
			throw e;
		}
		
		log.debug("### 뿌리기 지갑 생성 완료!");
	}
	
	@Override
	public void acceptSprinkle(ParameterDTO params, long money) throws Exception {
		log.debug("#### 뿌리기 지갑에서 수금한 금액 감소 중....");
		ParameterDTO lastParam = new ParameterDTO();
		BeanUtils.copyProperties(params, lastParam);
		lastParam.setMoney(money);
		
		try {
			log.debug("### 파라미터 확인 : "+lastParam.toString());
			sprinkleMapper.updateSprinkleStatus(lastParam);
			sprinkleMapper.updateSprinkleOrd(lastParam);
		} catch (Exception e) {
			log.error("### 수금한 금액 감소 중 에러 발생!!!!!");
			e.getStackTrace();
			throw e;
		}
		
		log.debug("### 뿌리기 지갑에서 수금한 금액 감소 완료!");
	}

	@Override
	public Sprinkle selectByParameter(ParameterDTO params, String switchVal) throws Exception {
		log.debug("#### 뿌리기 지갑 내용 조회 중...");
		
		Sprinkle sprinkle = new Sprinkle();
		
		try {
			switch (switchVal) {
			case "accept":		// 수금을 위한 SELECT ID
				sprinkle = sprinkleMapper.selectForAcceptMoney(params);
				break;
			case "sprinkle":		// 수금을 위한 SELECT ID
				sprinkle = sprinkleMapper.selectSprinkleByParams(params);
				break;
			default:			// (기본값) 뿌리기 상태 조회를 위한 SELECT ID
				sprinkle = sprinkleMapper.selectSprinkleByParams(params);
				break;
			}
		} catch (Exception e) {
			log.error("#### 뿌리기 지갑 조회 도중 에러 발생!");
			e.getStackTrace();
			throw e;
		}
		
		log.debug("#### 뿌리기 지갑 내용 조회 완료");
		
		return sprinkle;
	}
}
