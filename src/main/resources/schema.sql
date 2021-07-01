drop table if exists usuario;
create table if not exists usuario (
	id integer identity primary key, 
	nome varchar(255) not null,
	email varchar,
	senha text,
	ativo enum('S', 'N') default 'S',
	qtd_erro_senha integer default 0,
	expiracao_conta datetime,
	expiracao_senha datetime,
	
	constraint uq_usuario_1 unique(nome)
);

drop table if exists perfil;
create table if not exists perfil (
	id integer identity primary key, 
	nome varchar(255) not null,
	descricao varchar not null,
	ativo enum('S', 'N') default 'S',
	constraint uq_perfil_1 unique(nome)
);

drop table if exists usuario_perfil;
create table if not exists usuario_perfil (
	id integer identity primary key, 
	usuario_id integer not null,
	perfil_id integer not null,
	constraint uq_usuario_perfil_1 unique(usuario_id, perfil_id),
	foreign key (usuario_id) references usuario(id) on delete cascade on update cascade,
	foreign key (perfil_id) references perfil(id)
);
