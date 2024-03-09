CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers
values (1, 'Ivan', 'Petrov', 20, 'Vasilkovo'),
       (2, 'Serg', 'Ivanov', 25, 'Kukuevo'),
       (3, 'Irka', 'Sidorova', 17, 'Vlasiha');


select * from customers where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders
values (1, 100, 1),
       (2, 200, 2);


select first_name Имя, last_name Фамилия from customers c join orders o on c.id = o.customer_id where c.id in (select customer_id from orders);
