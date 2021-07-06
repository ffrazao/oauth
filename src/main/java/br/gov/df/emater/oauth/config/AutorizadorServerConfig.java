package br.gov.df.emater.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AutorizadorServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenEnhancer tokenEnhancer;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(new JdbcClientDetailsService(dataSource));
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).approvalStore(new JdbcApprovalStore(dataSource))
				.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource))
				.tokenStore(new JdbcTokenStore(dataSource)).tokenEnhancer(tokenEnhancer);
	}

}
