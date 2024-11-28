use ecommerce_db;

drop table if exists reservations;

create table reservations (
    reservation_id mediumint primary key auto_increment,
    user_id mediumint not null,
    creation_datetime timestamp default current_timestamp not null,
    expiration_datetime timestamp default (current_timestamp + interval 30 minute) not null,
    status enum('ACTIVE', 'EXPIRED', 'USED') not null,
    foreign key(user_id) references users(user_id)
) auto_increment = 1000001;

select * from reservations;

-- truncate reservations;
-- alter table reservations auto_increment = 1000001;

-- desc reservations;

-- select now();
-- select now() + interval 30 minute;
