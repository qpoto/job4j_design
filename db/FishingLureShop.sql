create table if not exists brands(
	id serial primary key, 
	name text
);
	
create table if not exists lures(
	id serial primary key, 
	name text,
	brand_id int references brands(id)
);

create table if not exists brand_lure(
	id serial primary key, 
	brand_id int references brands(id),
	lures_id int references lures(id)
);

create table if not exists brand_exсlure(
	id serial primary key, 
	brand_id int references brands(id) unique,
	lures_id int references lures(id) unique
);

