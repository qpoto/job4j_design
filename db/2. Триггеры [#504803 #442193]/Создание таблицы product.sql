create table products (
    id serial primary key,
    name varchar(100),
    producer varchar(100),
    count integer default 0,
    price integer
);