package com.kakaopay.showmoney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.mappers.AcceptMapper;
import com.kakaopay.showmoney.data.model.AcceptMoney;
import com.kakaopay.showmoney.service.AcceptingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("acceptingService")
public class AcceptingServiceImpl implements AcceptingService {
	
	@Autowired
	AcceptMapper acceptMapper;
	
	@Override
	public void insetTakeMoney(AcceptMoney accept) throws Exception {
		log.debug("돈 받아가기 시도...");
		try {
			int temp = acceptMapper.InsertAcceptMoney(accept);
			log.debug("## INSERT 반환값 : "+temp);
		} catch (Exception e) {
			log.error("돈 받아가던 도중 에러 발생!");
			e.getStackTrace();
			e.getMessage();
			throw e;
		}
		log.debug("돈 받아가기 이력 생성 완료");
	}

	@Override
	public int selectHistory(ParameterDTO params) throws Exception {
		return acceptMapper.selectHistoryExist(params);
	}

	@Override
	public List<AcceptMoney> selectHistoryByParameter(ParameterDTO params) throws Exception {
		return acceptMapper.selectHistoryByParameter(params);
	}
}
