create table if not exists brands(
	id serial primary key, 
	name text
);

insert into brands (name) values ('A');
insert into brands (name) values ('Б');
insert into brands (name) values ('Ручная работа');
	
create table if not exists lures(
	id serial primary key, 
	name text,
	brand_id int references brands(id)
);

insert into lures (name, brand_id) values ('A', 1);
insert into lures (name, brand_id) values ('A', 2);
insert into lures (name, brand_id) values ('Б', 1);
insert into lures (name, brand_id) values ('Б', 2);

create table if not exists brand_lure(
	id serial primary key, 
	brand_id int references brands(id),
	lures_id int references lures(id)
);

insert into brand_id (name, brand_id) values (1, 1);
insert into brand_id (name, brand_id) values (1, 2);
insert into brand_id (name, brand_id) values (2, 1);
insert into brand_id (name, brand_id) values (2, 2);

create table if not exists brand_exсlure(
	id serial primary key, 
	brand_id int references brands(id) unique,
	lures_id int references lures(id) unique
);

select * from brand_exclure;



