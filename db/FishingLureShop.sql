create table if not exists brand(
    id serial primary key,
    brand varchar(255)
);

insert into Brand(brand) values ('HandMade');
insert into Brand(brand) values ('Evergreen');

create table if not exists softLures(
    id serial primary key,
    sort varchar(255)
);

insert into SoftLures(sort) values ('Силикон');
insert into SoftLures(sort) values ('Стример');

create table if not exists softLures_brand(
    id serial primary key,
    sort_id int references softlures(id),
	brand_id int references brand(id)
);

insert into softLures_brand(sort_id, brand_id) values (1000, 1);
insert into softLures_brand(sort_id, brand_id) values (2, 2);

select * from softLures_brand;

