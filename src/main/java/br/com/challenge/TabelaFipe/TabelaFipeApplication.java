package br.com.challenge.TabelaFipe;

import br.com.challenge.TabelaFipe.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeApplication  {

	private final Principal principal;

	public TabelaFipeApplication(Principal principal) {
		this.principal = principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

}
