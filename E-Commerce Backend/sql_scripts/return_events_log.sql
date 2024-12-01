-- use ecommerce_db;

drop table if exists return_events_log;

create table return_events_log (
    return_id mediumint primary key auto_increment,
    order_id mediumint not null,
    creation_datetime timestamp default current_timestamp not null,
    status enum('ACTIVE', 'CANCELLED', 'COMPLETED') not null,
    status_change_datetime timestamp default null,
    foreign key(order_id) references orders(order_id)
) auto_increment = 1000001;

select * from return_events_log;

-- desc return_events_log;
