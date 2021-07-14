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

		http.authorizeRequests().antMatchers("/", "/h2-console/**", "/ping").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
		http.authorizeRequests().antMatchers("/**").authenticated();

		http.csrf().disable();
		http.headers().frameOptions().disable();
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

}
