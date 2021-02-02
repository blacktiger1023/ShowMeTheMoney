package com.kakaopay.showmoney.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 파라미터 전달을 위한 DTO
 * @author seon
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class ParameterDTO {
	/**	사용자ID	*/
	private int userId;
	/**	대화방ID	*/
	private String roomId;
	/**	대화방에 뿌릴 금액	*/
	private long money;
	/**	인원수	*/
	private int count;
	/** 토큰값 */
	private String tokens;
}
