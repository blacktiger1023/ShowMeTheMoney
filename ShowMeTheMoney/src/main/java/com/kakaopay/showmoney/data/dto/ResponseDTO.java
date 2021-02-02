package com.kakaopay.showmoney.data.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {

	private static DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
	
    // 응답시간
    private String time;
    // 응답 코드
    private String code;
    // 응답 메시지
    private String message;
    // 응답 바디
    private T body;

    // OK (그냥 OK)
    public static <T> ResponseDTO<T> OK(){
        return (ResponseDTO<T>)ResponseDTO.builder()
                .time(formatter.format(new Date()))
                .code("SUCCESS")
                .message("OK")
                .build();
    }


    // OK (데이터 포함)
    public static <T> ResponseDTO<T> OK(T data){
        return (ResponseDTO<T>)ResponseDTO.builder()
                .time(formatter.format(new Date()))
                .code("SUCCESS")
                .message("OK")
                .body(data)
                .build();
    }

    // ERROR
    public static <T> ResponseDTO<T> ERROR(String description){
        return (ResponseDTO<T>)ResponseDTO.builder()
                .time(formatter.format(new Date()))
                .code("FAIL")
                .message(description)
                .build();
    }
}

