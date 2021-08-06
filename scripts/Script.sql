
drop TABLE IF exists associate_portfolio;
drop TABLE IF exists employee_portfolio;
drop TABLE IF exists associate;
drop table if exists employee;


create table associate(
    salesforceId varchar(10) primary key not null, 
    firstname varchar(20) not null,
    lastname varchar(20) not null,
    email varchar(70),
    pswrd varchar(40)
);


create table associate_portfolio(
	salesforceId varchar(10) primary key not null,
    bio text,
    favorite_technologies text,
    preference varchar(15),
    foreign key (salesforceId)
    references associate(salesforceId)

);

create table employee (
    salesforceId INTEGER primary key not null, 
    firstname varchar(20) not null,
    lastname varchar(20) not null,
    email varchar(70),
    pswrd varchar(40)
);



create table employee_portfolio(
	salesforceId INTEGER primary key not null,
    bio text,
    technology text,
    trainer_location varchar(25),
    foreign key (salesforceId)
    references employee(salesforceId)
    
);

     
