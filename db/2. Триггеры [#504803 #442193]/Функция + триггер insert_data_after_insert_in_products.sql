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