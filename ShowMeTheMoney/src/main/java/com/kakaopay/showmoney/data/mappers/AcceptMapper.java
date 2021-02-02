package com.kakaopay.showmoney.data.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.model.AcceptMoney;

@Mapper
@Repository
public interface AcceptMapper {	
	/**
	 * 받기 데이터 생성
	 * @param acceptMoneyDTO
	 */
	public int InsertAcceptMoney(AcceptMoney acceptMoney);
	
	/**
	 * 수금 이력 있는지 확인
	 * @param params
	 * @return
	 */
	public int selectHistoryExist(ParameterDTO params);
	
	/**
	 * 이력조회
	 * @param params
	 * @return
	 */
	public List<AcceptMoney> selectHistoryByParameter(ParameterDTO params);
}
