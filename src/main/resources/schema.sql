drop table if exists usuario cascade;
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

drop table if exists perfil cascade;
create table if not exists perfil (
	id integer identity primary key, 
	nome varchar(255) not null,
	descricao varchar not null,
	ativo enum('S', 'N') default 'S',
	constraint uq_perfil_1 unique(nome)
);

drop table if exists usuario_perfil cascade;
create table if not exists usuario_perfil (
	id integer identity primary key, 
	usuario_id integer not null,
	perfil_id integer not null,
	constraint uq_usuario_perfil_1 unique(usuario_id, perfil_id),
	foreign key (usuario_id) references usuario(id) on delete cascade on update cascade,
	foreign key (perfil_id) references perfil(id)
);



-- ### tabelas do oauth ### --
-- used in tests that use HSQL
drop table if exists oauth_client_details cascade;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token cascade;
create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

drop table if exists oauth_access_token cascade;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token cascade;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

drop table if exists oauth_code cascade;
create table oauth_code (
  code VARCHAR(256), authentication LONGVARBINARY
);

drop table if exists oauth_approvals cascade;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
drop table if exists ClientDetails cascade;
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

