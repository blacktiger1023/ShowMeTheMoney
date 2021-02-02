package com.kakaopay.showmoney.service;

import java.util.List;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.model.AcceptMoney;

public interface AcceptingService {
	/**
	 * 돈 받아가기
	 * @return
	 * @throws Exception
	 */
	void insetTakeMoney(AcceptMoney accept) throws Exception;
	
	/**
	 * 수금한 이력 있는지 조회
	 * @param params
	 * @return
	 * @throws Exception
	 */
	int selectHistory(ParameterDTO params) throws Exception;
	
	/**
	 * 목록 조회
	 * 
	 */
	List<AcceptMoney> selectHistoryByParameter(ParameterDTO params) throws Exception;
}
