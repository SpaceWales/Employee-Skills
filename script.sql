BEGIN TRANSACTION;

DROP TABLE IF EXISTS address cascade;
DROP TABLE IF EXISTS employee cascade;
DROP TABLE IF EXISTS field cascade;
DROP TABLE IF EXISTS skill cascade;
DROP TABLE IF EXISTS employeeskills cascade;
DROP TABLE IF EXISTS businessunit cascade;
DROP TABLE IF EXISTS employeebusiness cascade;

CREATE TABLE employee 
(
        id serial not null,
        firstName varchar(64) not null,
        lastName varchar(64) not null,
        contactEmail varchar(64) unique,
        companyEmail varchar(64) unique,
        address_id int,
        birthDate date not null,
        hiredDate date,
        constraint pk_id primary key (id)
);

CREATE TABLE address 
(
        address_id serial not null, /*employee.id*/
        street varchar(64),
        suite varchar(64),
        city varchar(64),
        region varchar(3),
        postal varchar(5),
        country varchar(2),
        constraint pk_address_id primary key (address_id)
);

CREATE TABLE field
(
        field_id serial not null,
        field_name varchar(64) not null,
        field_type varchar(64) not null,
        constraint pk_field_id primary key (field_id)
);

CREATE TABLE skill
(
        skill_id serial not null,
        field_id int not null, /* field ID's */
        experience int not null,
        summary varchar(64),
        constraint exp_not_negative check (experience >= 0),
        constraint pk_skill_id primary key (skill_id),
        constraint fk_field_id foreign key (field_id) references field (field_id)
);

CREATE TABLE employeeskills
(
        employee_id int not null,
        skill_id int not null,
        constraint fk_employee_id foreign key (employee_id) references employee (id),
        constraint fk_skill_id foreign key (skill_id) references skill (skill_id)
);

CREATE TABLE businessunit
(
        company_id serial not null,
        company varchar(64) not null,
        constraint pk_company_id primary key (company_id)      
);

CREATE TABLE employeebusiness
(
        employee_id int not null,
        company_id int not null,
        constraint fk_employee_id foreign key (employee_id) references employee (id),
        constraint fk_company_id foreign key (company_id) references businessunit (company_id)
);

alter table employee
add foreign key (address_id)
references address (address_id);

COMMIT;




