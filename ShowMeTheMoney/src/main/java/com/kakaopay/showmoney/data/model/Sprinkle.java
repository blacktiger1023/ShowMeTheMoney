package com.kakaopay.showmoney.data.model;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Sprinkle {
	private String sprinkleId;			/**	뿌리기Id				*/
	private long createId;				/**	생성자 식별값			*/
	private String roomId;				/**	대화방 식별값			*/
	private int sprinkleNo;				/**	일련번호				*/
	private String tokens;				/**	토큰값				*/
	private long money;					/** 뿌린 금액				*/
	private long remainMoney;			/**	잔여 금액				*/
	private String dividedMoney;		/**	분배금액				*/
	private int moneyOrd;				/**	수금진행상태				*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createDt;				/**	생성날짜				*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String expiryDt;				/**	만료날짜				*/
}
