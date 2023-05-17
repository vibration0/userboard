# 이전 table를 지우고
drop table if exists users;

# 초기 스키마 정의
create table users(
UserId varchar(20) primary key not null,
PassWord text not null,
Name varchar(5) not null,
NickName varchar(6) unique not null,
PhoneNum int(12) not null,
EmailAddr varchar(25) not null,
Addr varchar(50) not null,
FulAddr varchar(50) not null,
CreatedAt timestamp default current_timestamp,
UpdatedAt timestamp null,
DeletedAt timestamp null
);
