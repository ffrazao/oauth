drop table if exists usuario;

create table if not exists usuario (
	id integer identity primary key, 
	nome varchar(255) not null,
	email varchar(255) not null
);
