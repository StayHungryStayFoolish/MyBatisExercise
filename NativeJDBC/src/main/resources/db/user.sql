show databases;
drop database if exists `mybatis_exercise`;
create database `mybatis_exercise` character set utf8 collate utf8_general_ci;

use `mybatis_exercise`;

drop table if exists `user`;
create table `user`
(
  id       bigint auto_increment primary key,
  username varchar(255),
  birthday date,
  sex      varchar(255),
  address  varchar(255)
);

show tables from `mybatis_exercise`;
show full columns from user;

select *
from user;
