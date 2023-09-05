create table if not exists departments(id serial primary key, name text);
create table if not exists employees(id serial primary key, name text, departments_id int references departments(id));
create table if not exists teens(name text, gender text);