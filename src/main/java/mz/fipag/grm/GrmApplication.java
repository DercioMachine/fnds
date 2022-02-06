package mz.fipag.grm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class GrmApplication {

	public static void main(String[] args) {

		SpringApplication.run(GrmApplication.class, args);

		System.out.println("Compilado com Sucesso ...");
	}

	/*
		@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GrmApplication.class);
    }
	 * @Bean public FilterRegistrationBean<Filter> disableSpringBootErrorFilter
	 * (ErrorPageFilter filter){ FilterRegistrationBean filterRegistrationBean = new
	 * FilterRegistrationBean(); filterRegistrationBean.setFilter(filter);
	 * filterRegistrationBean.setEnabled(false);
	 * 
	 * return filterRegistrationBean; }
	 */
}
