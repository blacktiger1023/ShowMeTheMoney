package com.kakaopay.showmoney.service;

public interface TokenGeneratorService {
	/**
	 * 토큰 생성하기
	 * @param length
	 * @return 생성된 토큰값
	 */
	StringBuffer generateTokens(int length);
	
	/**
	 * 토큰이 존재하는지 확인
	 * @param token	생성된 토큰값
	 * @return boolean 결과값(true : 동일한 토큰값 존재 / false : 토큰값 존재하지 않음-생성가능)
	 */
	boolean isExistTokens(String token);
}
