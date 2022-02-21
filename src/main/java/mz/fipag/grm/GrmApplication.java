package mz.fipag.grm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class GrmApplication extends SpringBootServletInitializer{
//public class GrmApplication {

	public static void main(String[] args)  {

		SpringApplication.run(GrmApplication.class, args);
		System.out.println("Compilado com Sucesso ...");

	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GrmApplication.class);
		
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("perdidoseachadosmoz@gmail.com");
		mailSender.setPassword("Machava@2020");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

    /*
	 * @Bean public FilterRegistrationBean<Filter> disableSpringBootErrorFilter
	 * (ErrorPageFilter filter){ FilterRegistrationBean filterRegistrationBean = new
	 * FilterRegistrationBean(); filterRegistrationBean.setFilter(filter);
	 * filterRegistrationBean.setEnabled(false);
	 * 
	 * return filterRegistrationBean; }
	 */
}
