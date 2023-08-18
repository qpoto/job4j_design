insert into rules(name) VALUES ('права_1');
insert into comments(name) VALUES ('комментарий_1');
insert into attachs(name) VALUES ('вложение_1');
insert into items(name, comments_id, attachs_id) VALUES ('заявка_1', 1, 1);
insert into users(name, items_id) VALUES ('пользователь_1', 1);
insert into roles(name, users_id) VALUES ('роль_1', 1);
insert into roles_rules(roles_id, rules_id) VALUES (1, 1);
insert into categories(name, items_id) VALUES ('категория_1', 1);
insert into states(name, items_id) VALUES ('категория_1', 1);




