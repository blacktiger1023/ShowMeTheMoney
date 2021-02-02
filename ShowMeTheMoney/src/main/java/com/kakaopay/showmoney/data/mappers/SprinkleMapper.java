package com.kakaopay.showmoney.data.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kakaopay.showmoney.data.dto.ParameterDTO;
import com.kakaopay.showmoney.data.model.Sprinkle;

@Mapper
@Repository
public interface SprinkleMapper {	
	/**
	 * 토큰값 존재하는지 확인
	 * @param token		생성예정 토큰값
	 * @return tokens	존재하는 토큰값
	 */
	public List<Sprinkle> selectSprinkleByToken(@Param("token") String token);
	
	/**
	 * 뿌리기 지갑 생성
	 * @param roomId 	대화방ID
	 * @param userId 	생성자ID
	 * @param amount 	금액
	 * @param count 	인원수
	 * @param token 	토큰값
	 */
	public void insertSprinkle(Sprinkle sprinkle);
	
	/**
	 * 파라미터 통해 수금된 금액 감소 시키기(money)
	 * @param params
	 */
	public int updateSprinkleStatus(ParameterDTO params);
	
	/**
	 * 파라미터 통해 수금된 금액 감소 시키기(Ord)
	 * @param params
	 */
	public int updateSprinkleOrd(ParameterDTO params);
	
	/**
	 * 파라미터 통하여 뿌리기 지갑 정보 가져오기
	 * @param roomId 	대화방ID
	 * @param userId 	생성자ID
	 * @param token 	토큰값
	 * @return Sprinkle
	 */
	public Sprinkle selectSprinkleByParams(ParameterDTO params);
	
	/**
	 * ParameterDTO 통하여 뿌리기 지갑 정보 가져오기
	 * @param roomId 	대화방ID
	 * @param userId 	생성자ID
	 * @param token 	토큰값
	 * @return Sprinkle
	 */
	public Sprinkle selectForAcceptMoney(ParameterDTO params);
}
