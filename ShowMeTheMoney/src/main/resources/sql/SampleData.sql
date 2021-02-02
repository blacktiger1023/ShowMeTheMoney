/**
 * 테스트를 위한 데이터 생성을 위한 SQL
 */

-- 테스트 사용자 생성
INSERT INTO showmethemoney.user_tb (user_id,user_nm,join_dt)
VALUES
(1,'홍길동',NOW()),
(2,'김대길',NOW()),
(3,'이동수',NOW()),
(4,'이철수',NOW()),
(5,'김개똥',NOW())
;

-- 대화방 생성
INSERT INTO showmethemoney.room_tb (room_id,room_nm,room_member,create_dt)
VALUES
('RM1000','일확천금방','1,2,3,4,5',NOW())
;
INSERT INTO showmethemoney.room_tb (room_id,room_nm,room_member,create_dt)
VALUES
('RM1001','로또방','1,2,3',NOW())
;
INSERT INTO showmethemoney.room_tb (room_id,room_nm,room_member,create_dt)
VALUES
('RM1002','어부지리방','1,4,5',NOW())
;
INSERT INTO showmethemoney.room_tb (room_id,room_nm,room_member,create_dt)
VALUES
('RM1003','오비이락방','1,2',NOW())
;
INSERT INTO showmethemoney.room_tb (room_id,room_nm,room_member,create_dt)
VALUES
('RM1004','불로소득방','1,5',NOW())
;
INSERT INTO showmethemoney.room_tb (room_id,room_nm,room_member,create_dt)
VALUES
('RM1005','돈벼락방','1,3,4',NOW())
;