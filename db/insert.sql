insert into roles(name) VALUES ('роль_1');
insert into rules(name) VALUES ('права_1');
insert into roles_rules(roles_id, rules_id) VALUES (1, 1);
insert into categories(name) VALUES ('категория_1');
insert into states(name) VALUES ('состояние_1');
insert into users(name, roles_id) VALUES ('пользователь_1', 1);
insert into items(name, categories_id, states_id, users_id) VALUES ('заявка_1', 1, 1, 1);
insert into comments(name, items_id) VALUES ('комментарий_1', 1);
insert into attachs(name, items_id) VALUES ('вложение_1', 1);







