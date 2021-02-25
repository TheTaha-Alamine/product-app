create table if not exists category(
	id bigint,
	name varchar(100) not null,
	primary key (id)
)

create table if not exists product(
	id bigint,
	name varchar(100) not null,
	price double not null,
	primary key (id),
	foreign key (category_id) references category(id)
)


create sequence if not exists hibernate_sequence start with 100;