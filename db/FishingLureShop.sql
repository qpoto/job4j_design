create table SoftLures(
    id serial primary key,
    sort varchar(255)
);


insert into SoftLures(sort) values ('Силикон');
insert into SoftLures(sort) values ('Стример');

select * from SoftLures;

create table HardLures(
    id serial primary key,
    sort varchar(255)
);

insert into HardLures(sort) values ('Воблер');
insert into HardLures(sort) values ('Блесна');

select * from HardLures;

create table Brand(
    id serial primary key,
    brand varchar(255)
);

insert into Brand(brand) values ('HandMade');
insert into Brand(brand) values ('Evergreen');

select * from Brand;
