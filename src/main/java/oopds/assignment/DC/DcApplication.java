package oopds.assignment.DC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This is the main Application Class that will start and initialize the web application
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
// @EnableJpaRepositories("oopds.assignment.DC.DAOs")
public class DcApplication {

	/**
	 * The main method
	 */
	public static void main(String[] args) {
		SpringApplication.run(DcApplication.class, args);
	}

	/**
	 * The method to initialize the Password Encoder that will encrypt the user's password
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
