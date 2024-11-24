use ecommerce_backend;

drop table if exists inventory_orders;

create table inventory_orders(
	inventory_id mediumint primary key,
    order_id mediumint,
    reservation_id mediumint not null,
    foreign key(inventory_id) references inventory(inventory_id),
    foreign key(order_id) references orders(order_id),
    foreign key(reservation_id) references reservations(reservation_id)
);

select * from inventory_orders;

insert into inventory_orders(inventory_id, order_id, reservation_id) values (1000024, null, 1000005);

desc inventory_orders;
