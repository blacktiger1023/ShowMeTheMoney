package com.kakaopay.showmoney.data.model;

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
public class AcceptMoney {
	private String eventId;					/**	수신이력ID(수금이력ID)	*/
	private String sprinkleId;				/**	뿌리기 지갑ID	*/
	private int acceptNo;					/**	일련번호		*/
	private long acceptId;					/**	수신자ID	*/
	private String roomId;					/**	대화방ID	*/
	private int takeMoney;					/**	수금 금액	*/
	private String eventDt;					/**	수금한 시각	*/
	private String tokens;					/**	토큰값	*/	
}
