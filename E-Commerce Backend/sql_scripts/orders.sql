-- use ecommerce_db;

drop table if exists orders;

create table orders (
	order_id mediumint primary key auto_increment,
    user_id mediumint not null,
    total_amount decimal(9, 2) not null,
    order_status enum('PENDING', 'CONFIRMED', 'PROCESSING', 'PACKAGED', 'SHIPPED', 
    'DELIVERED', 'CANCELLED', 'RETURNED', 'REFUNDED') not null,
    order_creation_datetime timestamp default current_timestamp not null,
    order_cancellation_datetime timestamp default null
) auto_increment = 1000001;

-- truncate orders;
-- alter table orders auto_increment = 1000001;

-- insert into orders(user_id, total_amount, order_status, 
-- order_cancellation_datetime) 
-- values
-- (1000001, 213.0, 'Pending', null),
-- (1000002, 56498, 'Pending', null);

select * from orders;

-- desc orders;
