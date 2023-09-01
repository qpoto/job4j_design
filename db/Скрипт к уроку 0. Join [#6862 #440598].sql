select * from employees e left join departments d on e.departments_id = d.id;
select * from employees e right join departments d on e.departments_id = d.id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees cross join departments;
select d.name as "Департамент без работников" from departments d left join employees e on e.departments_id = d.id where e.name is null;
select * from employees e left join departments d on e.departments_id = d.id where d.id is not null;
select * from employees e right join departments d on e.departments_id = d.id where e.id is not null;
select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender like 'w' and t1.gender != t2.gender order by t1.name;






