package br.gov.df.emater.oauth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.gov.df.emater.oauth.dao.UsuarioDao;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioDao dao) {
		return (args) -> {

			log.info("abrindo dados");
			log.info("usuarios {} listados", dao.findAll());
		};
	}

}
