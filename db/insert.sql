insert into rules(name) values ('права_1');
insert into comments(name) values ('комментрий_1');
insert into attachs(name) values ('вложение_1');
insert into categories(name) values ('категория_1');
insert into states(name) values ('состояние_1');
insert into roles(name,
				  rules_id)
				  values ('роль_1',
						 1);
insert into users(name, 
				  roles_id,
				  items_id)
				  values ('пользователь_1',
						 1,
						 1);
ALTER TABLE items RENAME COLUMN attachs TO attachs_id;
insert into items(name, 
				  comments_id, 
				  attachs_id, 
				  categories_id, 
				  states_id) 
				  values ('заявка_1',
						 1,
						 1,
						 1,
						 1);



