package mz.fipag.grm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GrmApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {

		SpringApplication.run(GrmApplication.class, args);

		System.out.println("Compilado com Sucesso ...");
	}


			@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GrmApplication.class);
    }
			
}
