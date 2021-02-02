package com.kakaopay.showmoney.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("commonUtils")
public final class CommonUtils {
	private static CommonUtils instance = new CommonUtils();
	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Calendar cal = Calendar.getInstance();
	private CommonUtils() {}
	public CommonUtils getInstances() {
		return instance;
	}
	
	/**
	 * Date 포맷의 String 값을 받아 Date 객체로 변환
	 * @param dateFormString
	 * @return
	 * @throws Exception
	 */
	public Date transFormStringToDate(String dateFormString) throws Exception {
		Date date;
		try {
			date = formatter.parse(dateFormString);
			return date;
		} catch (Exception e) {
			log.error("@ 날짜 값 파싱하는 도중 에러발생!");
			throw e;
		}
	}
	
	/**
	 * 현재시간 구하기(원하는 형태에 따라)
	 * @param dateFormat 데이트 포맷(타임스탬프)의 데이터
	 * @return	timestamp 형태의 String (기본값 : timestamp)
	 */
	public String getNowTimeStamp(String dateFormat){
        Date date = new Date();
        DateFormat easyFormmater;
        if("".equalsIgnoreCase(dateFormat)) {
        	easyFormmater = new SimpleDateFormat("yyyyMMddHHmmss");
        } else {
        	easyFormmater = new SimpleDateFormat(dateFormat);
        	try {
				return easyFormmater.format(date);
			} catch (Exception e) {
				easyFormmater = new SimpleDateFormat("yyyyMMddHHmmss");
			}
        }
        
        return easyFormmater.format(date);
    }
	
	/**
	 * 현재시간 구하기 ( 포맷 = "yyyy-MM-dd HH:mm:ss")
	 * @return
	 */
	public String getNowDate(){
        Date date = new Date();
        return formatter.format(date);
    }

    /**
     * Day 00 일 이후 시간 구하기
     * @param day	일수
     * @return
     */
    public String getSomeDayAfter(int day){
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, day);

        return formatter.format(cal.getTime());
    }

    /**
     * Minute 00 분 이후 시간 구하기
     * @param minute	분
     * @return
     */
    public String getSomeMinuteAfter(int minute){
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);

        return formatter.format(cal.getTime());

    }
	
	/**
	 * 돈 분배하기
	 * @param money
	 * @param count
	 * @return
	 */
	public String distributionMoney(long money, int count) {
		StringBuffer bf = new StringBuffer();
		long remainMoney = money;
		
		for(int i=0; i<count; i++) {			
			if(i!=count-1) {	// 돈 분배
				int temp = (int) Math.round((( Math.random()*remainMoney)+1)%remainMoney );
				bf.append(temp+",");
				remainMoney = remainMoney - temp;
			} else {	// 마지막 루프(잔돈 모두다 처리)
				bf.append(remainMoney);
			}
		}
		
		log.debug("돈 분배된 완료된 금액은 ["+bf.toString()+"] 입니다.");
		
		return bf.toString();
	}
}
