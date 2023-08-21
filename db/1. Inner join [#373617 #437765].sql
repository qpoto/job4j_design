select * from roles join users on roles.users_id = users_id;
select r.name as Роль, u.name as "Имя пользователя" from roles as r join users as u on r.users_id = r.id;
select r.name Роль, u.name as "Имя пользователя" from roles r join users u on r.users_id = r.id;
