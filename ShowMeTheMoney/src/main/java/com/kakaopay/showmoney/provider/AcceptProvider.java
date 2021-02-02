package com.kakaopay.showmoney.provider;

import com.kakaopay.showmoney.data.dto.AcceptMoneyDTO;
import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.dto.ResponseDTO;

public interface AcceptProvider {
	/**
	 * 돈 받아가기
	 * @param params
	 * @return
	 */
	public ResponseDTO<AcceptMoneyDTO> takeMoney(ParameterDTO params);
}
