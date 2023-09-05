create or replace function tax_plus_row_before()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger trigger_tax_plus_row_before
    before insert
    on products
    for each row
    execute procedure tax_plus_row_before();
	
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 100, 100);
select * from products;