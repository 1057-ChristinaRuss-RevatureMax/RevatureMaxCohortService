
drop TABLE IF exists associate;
drop table if exists employee;


create table associate(
	salesforceId INT primary key not null,
	bio text,
	favorite_technologies text,
	preference varchar(15)

);

create table employee(
	salesforceId INT primary key not null,
	bio text,
	technology text,
	trainer_location varchar(25)

);


     