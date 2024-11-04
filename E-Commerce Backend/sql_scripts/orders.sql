use ecommerce_backend;

drop table if exists orders;

create table orders (
	order_id mediumint primary key auto_increment,
    user_id mediumint not null,
    total_amount decimal(9, 2) not null,
    order_status enum('PENDING', 'CONFIRMED', 'PROCESSING', 'PACKAGED', 'SHIPPED', 
    'DELIVERED', 'CANCELLED', 'Returned', 'Refunded') not null,
    order_creation_datetime timestamp default current_timestamp not null,
    order_cancellation_datetime timestamp default null,
    payment_status enum('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED') not null,
    payment_method enum('CREDIT_CARD', 'DEBIT_CARD', 'PAYPAL', 
    'CASH_ON_DELIVERY') not null
) auto_increment = 1000001;

insert into orders(user_id, total_amount, order_status, 
order_cancellation_datetime, payment_status, payment_method) 
values
(1000001, 213.0, 'Pending', null, 'COMPLETED', 'DEBIT_CARD'),
(1000002, 56498, 'Pending', null, 'PENDING', 'CASH_ON_DELIVERY');

select * from orders;

desc orders;
