package br.gov.df.emater.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new JdbcUserDetails();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.anonymous();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().antMatchers("/", "/h2-console/**").permitAll();
		http.authorizeRequests().antMatchers("/ping").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
		http.authorizeRequests().antMatchers("/**").authenticated();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.userDetailsService(userDetailsServiceBean());
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
//	}

}
