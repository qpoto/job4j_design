create table comments(id serial primary key, name text);
create table attachs(id serial primary key, name text);
create table states(id serial primary key, name text);
create table categories(id serial primary key, name text);
create table rules(id serial primary key, name text);
create table roles(id serial primary key, name text,
				  rules_id int references rules(id));
create table items(id serial primary key, name text,
				  comments_id int references comments(id),
				  attachs_id int references attachs(id),
				  categories_id int references categories(id),
				  states_id int references states(id));
create table users (id serial primary key, 
				   name text,
				  roles_id int references roles(id),
				  items_id int references items(id));




