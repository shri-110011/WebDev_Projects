use ecommerce_backend;

drop table if exists orders;

create table orders (
	order_id mediumint primary key auto_increment,
    user_id mediumint,
    total_amount decimal(9, 2),
    status enum('Pending', 'Shipped', 'Delivered', 'Cancelled', 'Returned', 'Refunded'),
    order_creation_datetime timestamp default current_timestamp,
    order_cancellation_datetime timestamp default null
) auto_increment = 1000001;

desc orders;

insert into orders(user_id, total_amount, status, order_cancellation_datetime) 
values
(1000001, 213.0, 'Pending', null),
(1000002, 56498, 'Pending', null);

select * from orders;
