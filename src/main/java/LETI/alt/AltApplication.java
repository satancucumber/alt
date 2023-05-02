package LETI.alt;

import LETI.alt.Logical.Operator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan("LETI.alt.controller")
@EntityScan("LETI.alt.models")
@EnableJpaRepositories("LETI.alt.repo")
public class AltApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltApplication.class, args);
	}

}
