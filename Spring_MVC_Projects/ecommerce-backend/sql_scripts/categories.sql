-- use ecommerce_db;

drop table if exists categories;

create table categories (
	category_id tinyint primary key auto_increment,
    category_name varchar(45) not null
) auto_increment = 11;

insert into categories(category_name) values ('Electronics'), ('Fashion'), 
('Health and Beauty'), ('Home and Garden'), ('Sports and Outdoors'), 
('Books and Media'), ('Toys and Games'), ('Groceries');

select * from categories;

-- desc categories;
