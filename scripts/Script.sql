
drop TABLE IF exists associate;
drop table if exists employee;


create table associate(
	salesforceId varchar(10) primary key not null,
	bio text,
	favorite_technologies text,
	preference varchar(15)

);

create table employee(
	salesforceId varchar(10) primary key not null,
	bio text,
	technology text,
	trainer_location varchar(25)

);


     
