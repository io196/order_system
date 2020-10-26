create database order_system;
use order_system;

create table dishes (
dishId int primary key auto_increment,
name varchar(50),
price int
);

drop table if exists user;
create table user (
userId int primary key auto_increment,
password varchar(50),
name varchar(50) unique,
isAdmin int   --表示是否是管理员,0不是1是
);

create table order_user(
    orderId int primary key auto_increment,
    userId int,
    time datetime, --下单时间
    isDone int,   --1表示订单已完成,0表示未完成
    foreign key(userId) references user(userId)
);

create table order_dish (
orderId int,
dishId int,
foreign key(orderId) references order_user(orderId),
foreign key(dishId) references dishes(dishId)
);