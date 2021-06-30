package br.gov.df.emater.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OauthApplication {

	public static void main(String[] args) {
		log.info("Iniciando Servidor de Autorização!");
		SpringApplication.run(OauthApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(UsuarioDao dao) {
//		return (args) -> {
//
//			log.info("abrindo dados");
//			log.info("usuarios {} listados", dao.findAll());
//		};
//	}

}
