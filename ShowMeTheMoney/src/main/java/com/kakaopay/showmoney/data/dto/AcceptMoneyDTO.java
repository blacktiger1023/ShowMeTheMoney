package com.kakaopay.showmoney.data.dto;

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
public class AcceptMoneyDTO {
	private long userId;				/**	사용자 식별값			*/
	private String roomId;				/**	대화방 식별값			*/
	private long acceptMoney;			/** 받은 금액				*/
	private String tokens;				/**	토큰값				*/
}