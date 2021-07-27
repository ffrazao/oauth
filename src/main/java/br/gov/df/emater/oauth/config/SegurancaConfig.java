package br.gov.df.emater.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Olha aqui ver exemplo:
	 * https://www.baeldung.com/spring-security-5-oauth2-login para configurar os
	 * clients e
	 * https://stackoverflow.com/questions/32313821/integrate-spring-security-oauth2-and-spring-social
	 * https://spring.io/guides/tutorials/spring-security-and-angular-js/
	 * https://stackoverflow.com/questions/29547671/rest-spring-own-oauth2-server-oauth2-providers-like-facebook-google-yahoo
	 * https://www.javainuse.com/spring/spring-social
	 * https://keepcodeclean.com/google-facebook-oauth-spring/
	 * https://github.com/dineschandgr/spring-security-OAuth2-Facebook
	 * https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-2/
	 * ((((BOOOMMMMM ESTE É BOM))))
	 * 
	 * e-gov https://www.gov.br/conecta/catalogo/apis/brasil-cidadao-login-unico
	 * 
	 */

	@Value("${sistema.active-direcory.domain}")
	private String AD_DOMAIN;

	@Value("${sistema.active-direcory.search-filter}")
	private String AD_SEARCH_FILTER;

	@Value("${sistema.active-direcory.url}")
	private String AD_URL;

	@Autowired
	private JdbcUserDetails jdbcUserDetails;

	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider result = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN
		/* poderia ter sido usado o dominio "emater-df" porém preferi nenhum */, AD_URL);
		// result.setSearchFilter(AD_SEARCH_FILTER);
		result.setConvertSubErrorCodesToExceptions(true);
		result.setUseAuthenticationRequestCredentials(true);

		return result;
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.anonymous();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/", "/h2-console/**", /* "/ping", */ "/login*").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
		http.authorizeRequests().antMatchers("/**").authenticated();

		// http.oauth2Login().loginPage("/login");
		http.formLogin().loginPage("/login").successHandler(loginSuccessHandler());

		http.csrf().disable();
		http.headers().frameOptions().disable();

		// ver exemplo: https://www.baeldung.com/spring-security-5-oauth2-login para
		// configurar os clients

	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider result = null;
		result = new DaoAuthenticationProvider();
		result.setUserDetailsService(jdbcUserDetails);
		result.setPasswordEncoder(passwordEncoder());
		return result;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

}
