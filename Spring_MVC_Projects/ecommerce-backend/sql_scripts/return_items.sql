-- use ecommerce_db;

drop table if exists return_items;

create table return_items (
    inventory_event_id mediumint primary key,
	return_id mediumint,
    product_id  mediumint not null,
    quantity int not null,
    reason varchar(45),
    foreign key(return_id) references return_events_log(return_id),
    foreign key(inventory_event_id) references inventory_events_log(event_id)
);

select * from return_items;

-- desc return_items;
