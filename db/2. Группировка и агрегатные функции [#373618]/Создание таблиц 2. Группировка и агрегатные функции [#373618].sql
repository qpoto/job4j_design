create table if not exists devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table if not exists people(
    id serial primary key,
    name varchar(255)
);

create table if not exists devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
