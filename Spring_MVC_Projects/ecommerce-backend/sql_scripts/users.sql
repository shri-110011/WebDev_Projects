-- drop database if exists ecommerce_db;

create database if not exists ecommerce_db;

use ecommerce_db;

drop table if exists users;

create table users (
	user_id mediumint primary key auto_increment,
    user_name varchar(45) not null,
    email_id varchar(30) unique not null
) auto_increment = 1000001;

insert into users 
(user_name, email_id)
values
('John', 'john@gmail.com'),
('Peter', 'peter@gmail.com');

select * from users;

-- desc users;
