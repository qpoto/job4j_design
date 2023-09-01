select 
c.name "Модель", cb.name "Кузов", ce.name "Тип двигателя (топлива)", ct.name "Тип коробки" 
from car c 
full join car_bodies cb 
on c.body_id = cb.id 
full join car_engines ce 
on c.engine_id = ce.id
full join car_transmissions ct 
on c.transmission_id = ct.id;

select 
cb.name "Не используемый тип кузова" 
from car_bodies cb 
left join car c 
on c.body_id = cb.id 
where c.body_id is null;

select 
ce.name "Не используемый тип двигателя" 
from car_engines ce 
left join car c 
on c.engine_id = ce.id 
where c.engine_id is null;

select 
ct.name "Не используемый тип коробки" 
from car_transmissions ct 
left join car c 
on c.transmission_id = ct.id 
where c.transmission_id is null;
