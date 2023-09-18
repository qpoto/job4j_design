create or replace function tax_plus_row_before()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger trigger_tax_plus_row_before
    before insert
    on products
    for each row
    execute procedure tax_plus_row_before();