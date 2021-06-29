package br.gov.df.emater.oauth.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EntityScan("br.gov.df.emater.oauth.dominio")
@EnableTransactionManagement
public class BancoDadosConfig {

}
