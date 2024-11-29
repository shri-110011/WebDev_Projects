-- use ecommerce_backend;

drop table if exists inventory;

create table inventory (
	inventory_id mediumint primary key auto_increment,
    product_id mediumint not null,
    change_type enum('RESTOCK', 'SALE', 'RETURN', 'ADJUSTMENT', 
    'RESERVATION') not null,
    quantity int not null,
    creation_datetime timestamp default current_timestamp not null,
    reason varchar(45),
    status enum('ACTIVE', 'COMPLETED') not null,
    status_change_datetime timestamp default null,
    adjusted_by varchar(45),
    foreign key(product_id) references products(product_id)
) auto_increment = 1000001;

select * from inventory;

-- truncate inventory;
-- alter table inventory auto_increment = 1000001;

-- SET FOREIGN_KEY_CHECKS = 0;

-- desc inventory;

-- insert into inventory(product_id, change_type, quantity, status) values (1000005, 'RESERVATION', 1, 'ACTIVE');