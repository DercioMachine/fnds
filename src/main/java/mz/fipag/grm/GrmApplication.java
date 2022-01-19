package mz.fipag.grm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class GrmApplication {

	public static void main(String[] args) {

		SpringApplication.run(GrmApplication.class, args);

		System.out.println("Compilado com Sucesso ...");
	}

}
