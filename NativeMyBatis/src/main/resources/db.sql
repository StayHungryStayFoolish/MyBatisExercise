show databases;
drop database if exists `native_mybatis`;
create database `native_mybatis` character set utf8 collate utf8_general_ci;

use `native_mybatis`;

drop table if exists `person`;
create table `person`
(
  id       bigint auto_increment primary key,
  name varchar(255),
  sex      varchar(255),
  age  int
);

show tables from `native_mybatis`;
show full columns from person;

select *
from person;
