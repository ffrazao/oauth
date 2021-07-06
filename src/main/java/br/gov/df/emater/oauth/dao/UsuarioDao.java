package br.gov.df.emater.oauth.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.df.emater.oauth.entidade.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer>  {

	Usuario findByNome(String nome);
	
	@Modifying
	@Query("update Usuario u set u.qtdErroSenha = u.qtdErroSenha + 1 where u = :usuario")
	void incrementaQtdErroSenha(Usuario usuario);
	
	@Modifying
	@Query("update Usuario u set u.qtdErroSenha = 0 where u = :usuario")
	void reiniciaQtdErroSenha(Usuario usuario);

}
