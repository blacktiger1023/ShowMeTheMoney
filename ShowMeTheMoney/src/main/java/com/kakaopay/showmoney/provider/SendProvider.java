package com.kakaopay.showmoney.provider;

import java.util.Map;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.dto.ResponseDTO;

/**
 * 토큰 발급을 위한 Provider 인터페이스
 * @author seon
 */
public interface SendProvider {
	/**
	 * 돈 뿌리기
	 * ParameteDTO 통해 전단
	 * @param userId	생성자ID
	 * @param roomId	대화방ID
	 * @param money		뿌릴금액
	 * @param count		뿌릴 인원 수
	 * @return ResponeEntity
	 */
	public ResponseDTO<Map<String,Object>> doSprinkle(ParameterDTO param);
	
	/**
	 * 뿌리기 지갑 상태 확인
	 * ParameteDTO 통해 전단
	 * @param token		토큰값
	 * @return ResponeEntity
	 */
	public ResponseDTO<Map<String,Object>> selectSprinkle(ParameterDTO param);
}
