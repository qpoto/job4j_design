create table if not exists fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)values('dino', 15000, '1930-01-01');
insert into fauna(name, avg_age, discovery_date)values('fish', 40000, '1980-05-01');
insert into fauna(name, avg_age, discovery_date)values('tiger', 25000, null);

select * from fauna where name like 'fish';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '2023-08-17';

select * from fauna;

