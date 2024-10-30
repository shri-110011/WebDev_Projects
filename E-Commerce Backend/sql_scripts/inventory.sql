use ecommerce_backend;

drop table if exists inventory;

create table inventory (
	inventory_id mediumint primary key auto_increment,
    product_id mediumint,
    order_id mediumint,
    change_type enum('Restock', 'Sale', 'Return', 'Adjustment', 'Reservation'),
    quantity int,
    creation_datetime timestamp default current_timestamp,
    reason varchar(45),
    status enum('Active', 'Cancelled', 'Completed'),
    status_change_datetime timestamp default null,
    adjusted_by varchar(45),
    
    foreign key(product_id) references products(product_id),
    foreign key(order_id) references orders(order_id)
) auto_increment = 1000001;

select * from inventory;
