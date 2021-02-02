/**
 * 원활한 서비스를 위해 사전에 필요한 테이블 생성을 위한 SQL(최초 구축 시)
 */

-- ※ 주의 : 기존 테이블 존재할 경우 삭제 구문. 영향도 확인필요
-- ※ 삭제 순서 변경 금지(외래키 의존성)
DROP TABLE showmethemoney.take_money_tb;
DROP TABLE showmethemoney.sprinkle_money_tb;
DROP TABLE showmethemoney.room_tb;
DROP TABLE showmethemoney.user_tb;

-- ※ 생성 순서 변경 금지(외래키 의존성)
-- 사용자
create table if not exists showmethemoney.user_tb  (
	user_id bigint unsigned not null primary key comment "사용자id"
	,user_no int unsigned not null unique key auto_increment comment "일련번호"
	,user_nm varchar(50) not null comment "사용자명"
	,join_dt datetime not null comment "가입날짜"
);

-- 대화방
create table if not exists showmethemoney.room_tb (
	room_id varchar(50) not null primary key comment "대화방id"
	,room_nm varchar(50) not null comment "대화방이름"
	,room_no int unsigned not null unique key auto_increment comment "일련번호"
	,room_member varchar(255) not null comment "참여자(id) 목록"
	,create_dt datetime not null comment "생성날짜"
);

-- 뿌리기_지갑
create table if not exists showmethemoney.sprinkle_money_tb (
	sprinkle_id varchar(255) not null primary key comment "뿌리기_지갑ID"
	,create_id bigint unsigned not null comment "사용자id"
	,room_id varchar(50) not null comment "대화방id"
	,sprinkle_no int unsigned not null unique key auto_increment comment "일련번호"
	,tokens varchar(50) not null unique key comment "토큰값"
	,money int unsigned not null comment "금액"
	,remain_money int unsigned not null comment "잔여금액"
	,divided_money varchar(255) not null comment "분배금액"
	,money_ord int not null comment "수금 진행 상태"
	,create_dt datetime not null comment "생성날짜"
	,expiry_dt datetime not null comment "만료날짜"
	,constraint fk_user_tb_sprinkle_money_tb foreign key(create_id) references user_tb(user_id)
	,constraint fk_room_tb_sprinkle_money_tb foreign key(room_id) references room_tb(room_id)
);

-- 수금자 이력 테이블
create table if not exists showmethemoney.take_money_tb  (
	event_id varchar(255) not null primary key comment "수금이력ID"
	,sprinkle_id varchar(255) not null comment "뿌리기_지갑ID"
	,accept_no int unsigned not null unique key auto_increment comment "일련번호"
	,accept_id bigint unsigned not null comment "수금자id"
	,room_id varchar(50) not null comment "대화방id"
	,take_money int unsigned not null comment "수금 금액"
	,tokens varchar(50) not null unique key comment "토큰값"
	,event_dt datetime not null comment "수금한 시각"
	,constraint fk_user_tb_take_money_tb foreign key(accept_id) references user_tb(user_id)
	,constraint fk_room_tb_take_money_tb foreign key(room_id) references room_tb(room_id)
	,constraint fk_sprinkle_money_tb_take_money_tb foreign key(sprinkle_id) references sprinkle_money_tb(sprinkle_id)
);