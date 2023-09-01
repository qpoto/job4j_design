insert into car_bodies (name) values ('SUV'), ('Sedan'), ('Hatchback'), ('Pickup');
insert into car_engines (name) values ('Gasoline'), ('Diesel'), ('Hybrid');
insert into car_transmissions (name) values ('AT'), ('MT'), ('7DCT');
insert into car (name, body_id, engine_id, transmission_id) values
('Haval Jolion', 1, 1, null),
('Audi A6', 2, 2, 2),
('BMW 1 series', 3, 1, 1),
('Moskvich 3e', 1, null, null),
('Skoda Octavia Liftback', null, 1, 2);