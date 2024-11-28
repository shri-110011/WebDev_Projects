use ecommerce_db;

drop table if exists order_items;

create table order_items (
	order_id mediumint,
    product_id mediumint,
    quantity tinyint not null,
    price_at_purchase decimal(8, 2) not null,
    primary key (order_id, product_id),
    foreign key(order_id) references orders(order_id),
    foreign key(product_id) references products(product_id)
);

-- insert into order_items(order_id, product_id, -- quantity, price_at_purchase)
-- values
-- (1000001, 1000015, 2, 50),
-- (1000001, 1000016, 1, 20),
-- (1000001, 1000014, 1, 93),
-- (1000002, 1000001, 1, 53499),
-- (1000002, 1000003, 1, 2999);

select * from order_items;

-- desc order_items;

-- truncate order_items;
