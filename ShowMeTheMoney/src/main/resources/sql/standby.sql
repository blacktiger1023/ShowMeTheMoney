/**
 * 최초 데이터베이스 생성 및 사용자 생성을 위한 SQL
 */

-- 데이터 베이스 생성(showmethemoney)
create database showmethemoney;

-- 사용자 계정 생성 ( ID : seon / PW : blacktiger)
create user 'seon'@'%' identified by 'blacktiger';

-- 생성한 사용자 계정에 권한 부여
grant all privileges on showmethemoney.* to 'seon'@'%';