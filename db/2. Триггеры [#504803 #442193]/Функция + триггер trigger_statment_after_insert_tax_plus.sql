create or replace function statment_after_insert_tax_plus()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
		where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger trigger_statment_after_insert_tax_plus
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure statment_after_insert_tax_plus();
