package org.correlaciones.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
//@ComponentScan({"org.correlaciones"})
/*
 * basePackages = {"guru.springframework.blog.componentscan.example.demopackageA",
        "guru.springframework.blog.componentscan.example.demopackageD",
        "guru.springframework.blog.componentscan.example.demopackageE"}*/
@ComponentScan(basePackages = {"org.correlaciones.configurarion",
        "org.correlaciones.model",
        "org.correlaciones.repository",
		"org.correlaciones.services"})
@PropertySource("propiedades.properties")
public class Configuracion {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
