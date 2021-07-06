package br.gov.df.emater.oauth.config;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import br.gov.df.emater.oauth.dao.UsuarioDao;
import br.gov.df.emater.oauth.dominio.Confirmacao;
import br.gov.df.emater.oauth.entidade.Usuario;

/**
 * Created by Frazão
 */
public class JdbcUserDetails implements UserDetailsService {

	@Autowired
	private UsuarioDao dao;

	@Value("${sistema.quantidade-tentativa-senha}")
	private Integer quantidadeTentativaSenha;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = dao.findByNome(username);
		
		if (usuario == null) {
			throw new BadCredentialsException("Acesso não autorizado");
		}
		
		Collection<GrantedAuthority> perfilList = usuario.getPerfilList().stream()
				.map(p -> new SimpleGrantedAuthority(
						String.format("ROLE_%s", p.getPerfil().getNome().replaceAll(" ", "_").toUpperCase())))
				.collect(Collectors.toList());

		User autorizacao = new User(usuario.getNome(), Optional.ofNullable(usuario.getSenha()).orElse(""),
				usuario.getAtivo().equals(Confirmacao.S),
				usuario.getExpiracaoConta() == null || usuario.getExpiracaoConta().isAfter(LocalDateTime.now()),
				usuario.getExpiracaoSenha() == null || usuario.getExpiracaoSenha().isAfter(LocalDateTime.now()),
				this.quantidadeTentativaSenha == null || usuario.getQtdErroSenha() < this.quantidadeTentativaSenha,
				perfilList);

		return autorizacao;
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();

		System.out.println(e.encode(new String("df_rural_mobile")));
		System.out.println(e.encode(new String("df_rural_web")));
		System.out.println(e.encode(new String("jose")));
	}

}
