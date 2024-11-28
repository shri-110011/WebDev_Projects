use ecommerce_db;

drop table if exists product_price_history;

create table product_price_history (
	history_id mediumint primary key auto_increment,
	product_id mediumint not null,
    old_price decimal(8,2),
    new_price decimal(8,2) not null,
    current_price_version smallint not null,
    price_change_datetime timestamp default current_timestamp not null,
    foreign key(product_id) references products(product_id)
)auto_increment = 1000001;

insert into product_price_history(product_id, old_price, old_version,
new_price, new_version)
select product_id, null, null, price, price_version from products;

select * from product_price_history;

-- select product_id, null, null, price, price_version from products;

-- desc product_price_history;
