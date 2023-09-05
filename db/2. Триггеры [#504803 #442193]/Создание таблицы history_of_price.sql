create table if not exists history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_data_after_insert_in_products()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date) values (new.name, new.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger trigger_insert_data_after_insert_in_products
    after insert on products
    for each row
    execute procedure insert_data_after_insert_in_products();

	
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 100, 100);
select * from history_of_price;