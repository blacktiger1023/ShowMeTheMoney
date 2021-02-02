package com.kakaopay.showmoney.service;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.model.Sprinkle;

public interface SendingService {
	/**
	 * 돈 뿌리기
	 * 
	 * @param roomId	대화방ID
	 * @param userId	생성자ID
	 * @param amount	금액
	 * @param count		인원수
	 * @param token		토큰값
	 * @return
	 */
	void sprinkle(Sprinkle sprinkle) throws Exception;
	
	/**
	 * 수금한 돈 뺴기
	 * @param params		파라미터
	 */
	void acceptSprinkle(ParameterDTO params, long money) throws Exception;
	
	/**
	 * 뿌린 돈 조회하기(파라미터 값 통해 조회)
	 * @param params		파라미터
	 * @param switchVal		매퍼 구분을 값( accept : 수금용 / sprinkle : 뿌리기 상태 조회용) 
	 * @return Sprinkle
	 */
	Sprinkle selectByParameter(ParameterDTO params, String switchVal) throws Exception;
}
