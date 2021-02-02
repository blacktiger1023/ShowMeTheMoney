package com.kakaopay.showmoney.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaopay.showmoney.constants.Data;
import com.kakaopay.showmoney.controller.ApiController;
import com.kakaopay.showmoney.data.mappers.SprinkleMapper;
import com.kakaopay.showmoney.data.model.Sprinkle;
import com.kakaopay.showmoney.service.TokenGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("tokenGeneratorService")
public class TokenGeneratorServiceImpl implements TokenGeneratorService {	
	@Autowired
	SprinkleMapper sprinkleMapper;
	
	@Override
	public StringBuffer generateTokens(int keySize) {
		StringBuffer sb = new StringBuffer();		// token값을 저장하기 위한 변수
		int seedSize = Data.TOKENSEED.length();		// 키값 생성을 위한 seed 크기
		Random rand = new Random();
		
		// 랜덤키 생성
		for(int i=0; i<keySize; i++) {
			sb.append(Data
					.TOKENSEED
					.charAt(rand.nextInt(seedSize)));	// 랜덤하게 생성된 숫자로 문자값 가져옴
		}
		
		log.debug("#### 랜덤 토큰값 : "+sb.toString());
		
		return sb;
	}
	
	@Override
	public boolean isExistTokens(String token) {
		String checkToken = token;
		List<Sprinkle> sprinkleData = sprinkleMapper.selectSprinkleByToken(checkToken);
		
		if(!sprinkleData.isEmpty()) {
			log.error("생성하고자 하는 토큰값이 기존에 존재합니다.");
			return true;
		} else {
			log.debug("토큰생성 가능");
			return false;
		}
	}
}
