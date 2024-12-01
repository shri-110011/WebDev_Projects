-- use ecommerce_db;

drop table if exists inventory_events_orders_reservations_link;

create table inventory_events_orders_reservations_link (
	inventory_event_id mediumint primary key,
    order_id mediumint,
    reservation_id mediumint not null,
    product_id mediumint not null,
    quantity int not null,
    price_at_purchase decimal(8,2),
    foreign key(inventory_event_id) references inventory_events_log(event_id),
    foreign key(order_id) references orders(order_id),
    foreign key(reservation_id) references reservations(reservation_id),
    foreign key(product_id) references products(product_id)
);

select * from inventory_events_orders_reservations_link;

-- truncate inventory_events_orders_reservations_link;

-- alter table inventory_events_orders_reservations_link auto_increment = 1000001;

-- insert into inventory_events_orders_reservations_link(inventory_event_id, order_id, reservation_id) values (1000024, null, 1000005);

-- desc inventory_events_orders_reservations_link;
