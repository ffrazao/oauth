package br.gov.df.emater.oauth.dao;

import org.springframework.data.repository.CrudRepository;

import br.gov.df.emater.oauth.entidade.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer>  {

	Usuario findByNome(String nome);

}
