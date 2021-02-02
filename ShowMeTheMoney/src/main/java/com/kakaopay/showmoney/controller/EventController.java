package com.kakaopay.showmoney.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.showmoney.data.dto.AcceptMoneyDTO;
import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.dto.ResponseDTO;
import com.kakaopay.showmoney.data.model.Sprinkle;
import com.kakaopay.showmoney.provider.AcceptProvider;
import com.kakaopay.showmoney.provider.SendProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RestController("eventController")
public class EventController {
	@Autowired
	SendProvider sender;
	
	@Autowired
	AcceptProvider accepter;
	
	/**
	 * 돈 뿌리기
	 * @param userId	생성자ID
	 * @param roomId	대화방ID
	 * @param money		
	 * @param count
	 * @return
	 */
	protected ResponseDTO<Map<String,Object>> sprinkleMoney(ParameterDTO param){
		if(param.getUserId()<1) {	// 1. 헤더 사용자ID 체크
			log.error("사용자 ID가 헤더에 없습니다.");
			return ResponseDTO.ERROR("사용자 ID가 헤더에 없습니다..");
		} else if("".equalsIgnoreCase(param.getRoomId())) {		// 2. 헤더 대화방ID 체크
			log.error("대화방 ID가 헤더에 없습니다.");
			return ResponseDTO.ERROR("대화방 ID가 헤더에 없습니다..");
		} else if(param.getMoney()<1) {			// 3. 금액 체크
			log.error("분배할 금액은 0원보다 크게 입력해주세요.");
			return ResponseDTO.ERROR("분배할 금액은 0원보다 크게 입력해주세요.");
		} else if(param.getCount()<1) {			// 4. 분배인원 체크
			log.error("분배할 인원을 1명 보다 크게 입력해주세요.");
			return ResponseDTO.ERROR("분배할 인원을 1명 보다 크게 입력해주세요.");
		} else {
			return sender.doSprinkle(param);
		}
	}
	
	/**
	 * 뿌린 돈 상태 조회
	 * @param userId	생성자ID
	 * @param roomId	대화방ID
	 * @param token		토큰값	
	 * @return
	 */
	protected ResponseDTO<Map<String,Object>> selectMoney(ParameterDTO param){
		if(param.getUserId()<1) {	// 1. 헤더 사용자ID 체크
			log.error("사용자 ID가 헤더에 없습니다.");
			return ResponseDTO.ERROR("사용자 ID가 헤더에 없습니다..");
		} else if("".equalsIgnoreCase(param.getRoomId())) {		// 2. 헤더 대화방ID 체크
			log.error("대화방 ID가 헤더에 없습니다.");
			return ResponseDTO.ERROR("대화방 ID가 헤더에 없습니다..");
		} else if("".equalsIgnoreCase(param.getTokens())) {			// 3. 토큰 체크
			log.error("토큰은 필수항목입니다.");
			return ResponseDTO.ERROR("토큰은 필수항목입니다.");
		} else {
			return sender.selectSprinkle(param);
		}
	}
	
	/**
	 * 돈 받기
	 * @param userId	생성자ID
	 * @param roomId	대화방ID
	 * @param money		
	 * @param count
	 * @return
	 */
	protected ResponseDTO<AcceptMoneyDTO> acceptMoney(ParameterDTO param){
		if(param.getUserId()<1) {	// 1. 헤더 사용자ID 체크
			log.error("사용자 ID가 헤더에 없습니다.");
			return ResponseDTO.ERROR("사용자 ID가 헤더에 없습니다..");
		} else if("".equalsIgnoreCase(param.getRoomId())) {		// 2. 헤더 대화방ID 체크
			log.error("대화방 ID가 헤더에 없습니다.");
			return ResponseDTO.ERROR("대화방 ID가 헤더에 없습니다..");
		} else if("".equalsIgnoreCase(param.getTokens())) {			// 3. 토큰 체크
			log.error("토큰은 필수항목입니다.");
			return ResponseDTO.ERROR("토큰은 필수항목입니다.");
		} else {
			return accepter.takeMoney(param);
		}
	}
}
