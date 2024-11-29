-- use ecommerce_db;

drop table if exists return_events_log;

create table return_events_log (
    inventory_event_id mediumint primary key,
    quantity int not null,
    creation_datetime timestamp default current_timestamp not null,
    reason varchar(45),
    status enum('ACTIVE', 'CANCELLED', 'COMPLETED') not null,
    status_change_datetime timestamp default null,
    foreign key(inventory_event_id) references inventory_events_log(event_id)
);

select * from return_events_log;

-- desc return_events_log;
