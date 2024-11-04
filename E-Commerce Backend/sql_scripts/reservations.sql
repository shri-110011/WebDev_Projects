use ecommerce_backend;

drop table if exists reservations;

create table reservations (
    reservation_id mediumint primary key auto_increment,
    user_id mediumint not null,
    creation_datetime timestamp default current_timestamp not null,
    expiration_datetime timestamp default (current_timestamp + interval 30 minute) not null,
    status enum('Active', 'Expired') not null,
    foreign key(user_id) references users(user_id)
) auto_increment = 1000001;

select * from reservations;

desc reservations;

select now();
select now() + interval 30 minute;
