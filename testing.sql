insert into employee (firstName,lastName,birthDate) values('jack','smith','1990-01-01');
insert into employee (firstName,lastName,birthDate) values('bob','jones','1990-01-05');
insert into employee (firstName,lastName,birthDate) values('delete','me','1990-01-05');
insert into field (field_name,field_type) values('java','software development');
insert into field (field_name,field_type) values('postgres','software development');
insert into field (field_name,field_type) values('c++','software development');
insert into field (field_name,field_type) values('recruiting','human resources');
insert into field (field_name,field_type) values('ruby','software development');

insert into address(street,city,region,postal,country) values ('123 nowhere lane','detroit','MI',48336,'US');

insert into skill (field_id,experience,summary) values (3,12,'java bootcamp'); 
insert into skill (field_id,experience,summary) values(4,6,'database course');

--update employee set skills = '{1,2}' where id = 1;

select * from employee where id = 1;
update employee set address_id = 1 where employee.id = 2;

--update employee set skills = skills || '{3,4,5}';

insert into employeeskills(employee_id,skill_id) values (2,3);
insert into employeeskills(employee_id,skill_id) values (1,2);

insert into businessunit (company) values ('IBM');
insert into businessunit (company) values ('Tech Elevator');

insert into employeebusiness (employee_id,company_id) values (6,1);
insert into employeebusiness (employee_id,company_id) values (6,2);

update employee
set address_id = 1
where id = 4;

select distinct businessunit.company, concat(employee.firstname,' ',employee.lastname) as employee 
from employeebusiness,employee,businessunit
where employee.id = 1;

select businessunit.company from businessunit
join employeebusiness on employeebusiness.company_id = businessunit.company_id
join employee on employee.id = employeebusiness.employee_id
where employee.id = 1; 

select field.field_name from field 
join skill on skill.field_id = field.field_id
join employeeskills on skill.skill_id = employeeskills.skill_id
join employee on employee.id = employeeskills.employee_id
where employee.id = 1 and skill.skill_id = 1 limit 1;

insert into employeebusiness(employee_id,company_id)
values (3,1), (3,2);

select distinct businessunit.company_id, businessunit.company 
from businessunit,employeebusiness
where employeebusiness.employee_id = ?;

insert into field(field_name)
values("testing ID return")
returning "field_id";

--giant mapped employee list
select * from employee
left join employeebusiness on employee.id = employeebusiness.employee_id
left join employeeskills on employee.id = employeeskills.employee_id
left join skill on skill.skill_id = employeeskills.skill_id
left join field on field.field_id = skill.field_id
left join businessunit on employeebusiness.company_id = businessunit.company_id
left join address on address.address_id = employee.address_id;

select * from employee
join address on address.address_id = employee.address_id;

select * from employee,address
where employee.id = 4
limit 1;

select * from employee,employeeskills,skill,field
where employee.id = 1 and skill.skill_id = 1
limit 1;

begin transaction;
delete from employeebusiness
where employee_id = 3;
delete from employeeskills
where employee_id = 3;
delete from employee
where id = 3;
commit;

select * from skill,field


begin transaction;
delete from employeeskills
where employee_id = ? and skill_id = ?;
delete from skill
where skill_id = ?;
commit;





--get all employees
select concat(lastname,',',firstname), companyemail, hireddate
from employee;

--post employee full
insert into employee
(firstname,lastname,contactemail,companyemail,birthdate,hireddate)
values('Will','Wales','wwales@email.com','w.wales@job.com','1990-05-04','2021-06-04');

--get employee by id
select concat(lastname,',',firstname), companyemail, hireddate
from employee
where id = 3;

--put employee by id
update employee 
set contactemail = 'jsmith@email.com'
where id = 1;

--delete employee by id
delete from employee 
where id = 3;

--get all skills by employee id
select
field.field_name,field.field_type,
skill.experience,skill.summary
from field
join skill on skill.field_id = field.field_id
join employeeskills on employeeskills.skill_id = skill.skill_id
join employee on employee.id = employeeskills.employee_id
where employee.id = 1 and skill.skill_id = 1 limit 1


--post a skill to employee
--there's gotta be a way to codense this down
insert into field (field_name,field_type)
values ('chef','cooking');

insert into skill (field_id,experience,summary)
select field.field_id,12,'master chef'
from field where field_name = 'chef';

insert into employeeskills
(skill_id,employee_id) select skill_id,3 from skill
join field on field.field_id = skill.field_id;


--get skill by skill id and employee id
select field_name from field 
join skill on field.field_id = skill.field_id
join employeeskills on employeeskills.skill_id = skill.skill_id
join employee on employee.id = employeeskills.employee_id
where employee.id = 1 and skill.skill_id = 1;


--put update a skill by employee and skill id
--this will update either skill or field depending on input

--skill first
update skill
set experience = 36
from employeeskills
where skill.skill_id = 4 and employeeskills.employee_id = 3;

--field
update field
set field_name = 'crappy chef'
from skill
join employeeskills on employeeskills.skill_id = skill.skill_id
where field.field_id = skill.field_id and skill.skill_id = 4 and employeeskills.employee_id = 3;

--delete 
delete from employeeskills 
where skill_id = 4 and employee_id = 3;



