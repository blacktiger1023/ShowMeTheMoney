# 카카오페이 뿌리기 기능 구현

## 목차
1. 개발환경
2. ERD
3. API 명세 및 실행 방법
4. 느낀점

## 개발환경
* Java 8
* Spring boot
* Mybatis + MariaDB

## 핵심문제 해결 전략 및 느낀점

1. 기능을 Sending 과 Accepting 으로 구분지어 개발하려고 했습니다. Controller - Provider - Service -Mapper 의 구성으로 개발했습니다.
2. 최대한 많이 예외 처리하고, 많은 것을 만들고 싶었으나...생각보다 뜻대로 되지 않았습니다.
3. 디자인 패턴도 많이 사용해보려고 시도는 했으나 잘 되지 않고, 결국 하던대로 많이 개발하게 되었습니다.

## ERD



## API 명세
0. Header
X-USER-ID : 사용자ID  -- 숫자
X-ROOM-ID : 대화방ID  -- 문자

1. 토큰 발행 ("api/rest/sprinkle")

<GET>
* 파라미터 : money - 금액 / count - 인원수
<POST>
* BODY : money - 금액 / count - 인원수

- 결과값

{
    "time": "2021-02-34 09:05:06",
    "code": "SUCCESS",
    "message": "OK",
    "body": {
        "result": {
            "time": "2021-02-34 09:05:06",
            "code": "SUCCESS",
            "message": "OK",
            "body": {
                "sprinkleId": "SP202102030905062",
                "createId": 2,
                "roomId": "RM1000",
                "sprinkleNo": 0,
                "tokens": "zdR",
                "money": 1000,
                "remainMoney": 1000,
                "dividedMoney": "468,77,217,19,21,38,78,19,55,8",
                "moneyOrd": 0,
                "createDt": "2021-02-03 09:05:06",
                "expiryDt": "2021-02-03 09:15:06"
            }
        }
    }
}

2. 받기("/api/rest/accept")

<GET>
* 파라미터 : tokens - 토큰값
<POST>
* BODY : tokens - 토큰값

{
    "time": "2021-02-34 09:08:07",
    "code": "SUCCESS",
    "message": "OK",
    "body": {
        "userId": 1,
        "roomId": "RM1000",
        "acceptMoney": 468,
        "tokens": "zdR"
    }
}

3. 조회("/api/rest/select")

<GET>
* 파라미터 : tokens - 토큰값
<POST>
* BODY : tokens - 토큰값
  
 {
    "time": "2021-02-34 09:14:14",
    "code": "SUCCESS",
    "message": "OK",
    "body": {
        "result": {
            "createId": 0,
            "roomId": "RM1000",
            "tokens": "zdR",
            "money": 532,
            "createDt": "2021-02-03T00:05:06.000+00:00",
            "remainMoney": 0,
            "acceptList": []
        }
    }
}

