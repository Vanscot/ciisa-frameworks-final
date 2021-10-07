package cl.ciisa.frameworks.simuladordecreditos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cl.ciisa.frameworks.simuladordecreditos.repository.ClienteRepository;

@SpringBootApplication
public class AdministradorDeCreditosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministradorDeCreditosApplication.class, args);
	}

}
