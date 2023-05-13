package com.hm.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class NoronApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoronApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer () {

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings (CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedHeaders("*")
						.allowedMethods("*")
						.allowedOrigins("*")
						.allowCredentials(false).maxAge(3600);
			}
		};




	}

}
