package com.kakaopay.showmoney.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopay.showmoney.constants.Data;
import com.kakaopay.showmoney.data.dto.AcceptMoneyDTO;
import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.dto.ResponseDTO;
import com.kakaopay.showmoney.data.model.Sprinkle;

import lombok.extern.slf4j.Slf4j;

/**
 * 메인 컨트롤러
 * GET, POST 메소드 처리하여 해당하는 Provider 호출 
 * @author seon
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/rest")
public class ApiController {
	@Autowired
	EventController eventController;
	
	/**
	 * POST 메소드 매핑(뿌리기)
	 */
	@PostMapping(value="/sprinkle")
	@ResponseBody
	public ResponseDTO<Map<String,Object>> doPostSprinkle(
			@RequestHeader(Data.HEADER_USER_ID) int userId,
			@RequestHeader(Data.HEADER_ROOM_ID) String roomId,
			@RequestBody ParameterDTO param)
	{
		ParameterDTO params = new ParameterDTO();
		BeanUtils.copyProperties(param, params);
		// 헤더 파라미터 DTO에 세팅
		params.setUserId(userId)
			.setRoomId(roomId);
		log.debug("########## POST :::: [ userId="+userId+" , roomId="+roomId+" , money="+params.getMoney()+" , count="+params.getCount()+" ] ##########");
		return eventController.sprinkleMoney(params);
	}
	
	/**
	 * GET 메소드 매핑(뿌리기)
	 */
	@GetMapping(value="/sprinkle")
	public ResponseDTO<Map<String,Object>> doGetSprinkle(
			@RequestHeader(Data.HEADER_USER_ID) int userId,
			@RequestHeader(Data.HEADER_ROOM_ID) String roomId,
			@RequestParam("money") long money,
			@RequestParam("count") int count)
	{
		ParameterDTO params = new ParameterDTO();
		// 파라미터 DTO에 세팅
		params.setUserId(userId)
			.setRoomId(roomId)
			.setMoney(money)
			.setCount(count);
		
		log.debug("########## GET :::: [ userId="+userId+" , roomId="+roomId+" , money="+params.getMoney()+" , count="+params.getMoney()+" ] ##########");
		return eventController.sprinkleMoney(params);
	}
	
	/**
	 * POST 메소드 매핑(조회)
	 */
	@PostMapping(value="/select")
	@ResponseBody
	public ResponseDTO<Map<String,Object>> doPostSelect(
			@RequestHeader(Data.HEADER_USER_ID) int userId,
			@RequestHeader(Data.HEADER_ROOM_ID) String roomId,
			@RequestBody ParameterDTO param)
	{
		ParameterDTO params = new ParameterDTO();
		BeanUtils.copyProperties(param, params);
		// 헤더 파라미터 DTO에 세팅
		params.setUserId(userId)
			.setRoomId(roomId);
		log.debug("########## POST :::: [ userId="+userId+" , roomId="+roomId+" , money="+params.getMoney()+" , count="+params.getCount()+" ] ##########");
		return eventController.selectMoney(params);
	}
	
	/**
	 * GET 메소드 매핑(조회)
	 */
	@GetMapping(value="/select")
	public ResponseDTO<Map<String,Object>> doGetSelect(
			@RequestHeader(Data.HEADER_USER_ID) int userId,
			@RequestHeader(Data.HEADER_ROOM_ID) String roomId,
			@RequestParam("tokens") String tokens)
	{
		ParameterDTO params = new ParameterDTO();
		
		// 파라미터 DTO에 세팅
		params.setUserId(userId)
			.setRoomId(roomId)
			.setTokens(tokens);
		
		log.debug("########## GET :::: [ userId="+userId+" , roomId="+roomId+" , money="+params.getMoney()+" , count="+params.getMoney()+" ] ##########");
		return eventController.selectMoney(params);
	}
	
	/**
	 * POST 메소드 매핑(수금하기)
	 */
	@PostMapping(value="/accept")
	@ResponseBody
	public ResponseDTO<AcceptMoneyDTO> doPostAccept(
			@RequestHeader(Data.HEADER_USER_ID) int userId,
			@RequestHeader(Data.HEADER_ROOM_ID) String roomId,
			@RequestBody ParameterDTO param)
	{
		ParameterDTO params = new ParameterDTO();
		BeanUtils.copyProperties(param, params);
		// 헤더 파라미터 DTO에 세팅
		params.setUserId(userId)
			.setRoomId(roomId);
		log.debug("########## POST :::: [ userId="+userId+" , roomId="+roomId+" , money="+params.getMoney()+" , count="+params.getCount()+" ] ##########");
		return eventController.acceptMoney(params);
	}
	
	/**
	 * GET 메소드 매핑(수금하기)
	 */
	@GetMapping(value="/accept")
	public ResponseDTO<AcceptMoneyDTO> doGetAccept(
			@RequestHeader(Data.HEADER_USER_ID) int userId,
			@RequestHeader(Data.HEADER_ROOM_ID) String roomId,
			@RequestParam("tokens") String tokens)
	{
		ParameterDTO params = new ParameterDTO();
		// 파라미터 DTO에 세팅
		params.setUserId(userId)
			.setRoomId(roomId)
			.setTokens(tokens);
		
		log.debug("########## GET :::: [ userId="+userId+" , roomId="+roomId+" , money="+params.getMoney()+" , count="+params.getMoney()+" ] ##########");
		return eventController.acceptMoney(params);
	}
}
