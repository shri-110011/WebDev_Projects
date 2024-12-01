-- use ecommerce_db;

drop table if exists inventory_events_log;

create table inventory_events_log (
	event_id mediumint primary key auto_increment,
    product_id mediumint not null,
    event_type enum('RESTOCK', 'SALE', 'ADJUSTMENT', 'RESERVATION') not null,
    quantity int not null,
    creation_datetime timestamp default current_timestamp not null,
    reason varchar(45),
    status enum('ACTIVE', 'COMPLETED') not null,
    status_change_datetime timestamp default null,
    adjusted_by varchar(45),
    foreign key(product_id) references products(product_id)
) auto_increment = 1000001;

select * from inventory_events_log;

-- truncate inventory_events_log;
-- alter table inventory_events_log auto_increment = 1000001;

-- SET FOREIGN_KEY_CHECKS = 1;

-- desc inventory_events_log;

-- insert into inventory_events_log(product_id, change_type, quantity, status) values (1000005, 'RESERVATION', 1, 'ACTIVE');