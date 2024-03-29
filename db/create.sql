create table if not exists roles(id serial primary key, name text);
create table if not exists rules(id serial primary key, name text);
create table if not exists roles_rules(id serial primary key,
						roles_id int references roles(id),
						rules_id int references rules(id));
create table if not exists categories(id serial primary key, name text);
create table if not exists states(id serial primary key, name text);
create table if not exists users (id serial primary key, name text,
                  roles_id int references roles(id));
create table if not exists items(id serial primary key, name text,
				  categories_id int references categories(id),
				  states_id int references states(id),
				  users_id int references users(id));
create table if not exists comments(id serial primary key, name text,
								   items_id int references items(id));
create table if not exists attachs(id serial primary key, name text,
								  items_id int references items(id));








