select * from product as p join type as t on p.type_id = t.id where t.name like 'Сыр';

select * from product where name like '%мороженое%';

select * from product where expired_date > current_date;

select * from product  where price=(select MAX(price) from product);

select t.name, count(t.name) from product as p join type as t on p.type_id = t.id group by t.name; 

select * from product as p join type as t on p.type_id = t.id where t.name like 'Сыр' or t.name like 'Молоко';

select tname, count(tname) from (select t.name tname from type t inner join product p on t.id=p.type_id) tt
group by tname
having count(tname)<10;

select p.name, t.name from product as p join type as t on p.type_id = t.id;



