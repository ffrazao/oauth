insert into usuario (id, nome, email) values (1, 'jose', 'jose@teste.com.br');
insert into usuario (id, nome, email) values (2, 'maria', 'maria@teste.com.br');

insert into usuario (id, nome, email, ativo, qtd_erro_senha, expiracao_conta, expiracao_senha) values (3, 'joao', 'joao@teste.com.br', 'N', 5, parsedatetime('30/06/2021 10:57:21.690', 'dd/MM/yyyy hh:mm:ss.SS'), parsedatetime('30/06/2021 18:47:52.690', 'dd/MM/yyyy hh:mm:ss.SS'));
insert into usuario (id, nome, email, ativo, qtd_erro_senha, expiracao_conta, expiracao_senha) values (4, 'ana', 'ana@teste.com.br', 'N', 5, parsedatetime('30/06/2021 10:57:21.690', 'dd/MM/yyyy hh:mm:ss.SS'), parsedatetime('30/06/2021 18:47:52.690', 'dd/MM/yyyy hh:mm:ss.SS'));

insert into perfil (id, nome, descricao) values (1, 'ADMIN', 'Administradores do sistema');
insert into perfil (id, nome, descricao) values (2, 'USUARIO', 'Usuários comuns do sistema1');
insert into perfil (id, nome, descricao) values (3, 'PRODUTOR', 'Usuários que também são assistidos pela empresa');
insert into perfil (id, nome, descricao) values (4, 'FUNCIONARIO', 'Funcionários da empresa');
insert into perfil (id, nome, descricao) values (5, 'GERENTE', 'Gerentes da empresa');
insert into perfil (id, nome, descricao) values (6, 'DIRETOR', 'Diretor da empresa');
insert into perfil (id, nome, descricao) values (7, 'PRESIDENTE', 'Presidente da empresa');

insert into usuario_perfil(usuario_id, perfil_id) values (1, 1);
insert into usuario_perfil(usuario_id, perfil_id) values (1, 2);

insert into usuario_perfil(usuario_id, perfil_id) values (2, 2);
insert into usuario_perfil(usuario_id, perfil_id) values (2, 4);

