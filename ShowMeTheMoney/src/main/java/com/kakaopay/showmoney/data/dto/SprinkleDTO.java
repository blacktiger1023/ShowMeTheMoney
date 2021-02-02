package com.kakaopay.showmoney.data.dto;

import java.util.Date;
import java.util.List;

import com.kakaopay.showmoney.data.model.AcceptMoney;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class SprinkleDTO {
	private long createId;					/**	사용자 식별값			*/
	private String roomId;					/**	대화방 식별값			*/
	private String tokens;					/**	토큰값				*/
	private long money;						/** 뿌린 금액				*/
	private Date createDt;					/**	뿌린 시각				*/
	private int remainMoney;				/**	현재 잔여 금액			*/
	private List<AcceptMoney> acceptList; /**	수금자 정보 리스트	*/
}
