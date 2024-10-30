-- drop database web_customer_tracker;

-- create database if not exists web_customer_tracker;

use web_customer_tracker;

drop table if exists customer;

create table customer (
	id int(11) primary key not null auto_increment,
    first_name varchar(45) default null,
    last_name varchar(45) default null,
    email varchar(45) default null
);

insert into customer values 
	(1,'David','Adams','david@gmail.com'),
	(2,'John','Doe','john@gmail.com'),
	(3,'Ajay','Rao','ajay@gmail.com'),
	(4,'Mary','Public','mary@gmail.com'),
	(5,'Maxwell','Dixon','max@gmail.com');
    
select * from customer;