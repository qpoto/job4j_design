create table staff(id serial primary key, name text, lastname text, year text, position text, children text);
insert into staff(name, lastname, year) values('Slava', 'Mikhailov', '1988');
select * from staff;
update staff set name = 'Stanislav';
select * from staff;
delete from staff;
select * from staff;
