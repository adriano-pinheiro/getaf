package br.com.fiap.adj.techchallenge.getaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GetafApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetafApplication.class, args);
	}

}
