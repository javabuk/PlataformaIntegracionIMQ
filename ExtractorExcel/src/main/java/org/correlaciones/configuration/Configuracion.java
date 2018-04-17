package org.correlaciones.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan({"org.correlaciones"})
@PropertySource("app.properties")
public class Configuracion {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Value("${sql.conexion.cadena}")
	private String cadenaConexionBBDD;
	
	@Value("${sql.conexion.usuario}")
	private String usuarioBBDD;
	
	@Value("${sql.conexionGestor.cadena}")
	private String cadenaConexionGestorBBDD;
	
	@Value("${sql.conexionGestor.usuario}")
	private String usuarioGestorBBDD;
	
	@Value("${sql.conexionGestor.password}")
	private String passwordGestorBBDD;
	
	@Bean(name="DataSourceINFO")
	  public DataSource dataSource() {
	      DriverManagerDataSource ds = new DriverManagerDataSource();
	      ds.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
	      ds.setUrl(cadenaConexionBBDD);
	      ds.setUsername(usuarioBBDD);
	      ds.setPassword(usuarioBBDD);
	      return ds;
	  }
	
	@Bean(name="DataSourceGestorPeticiones")
	  public DataSource dataSourceGestorPeticiones() {
	      DriverManagerDataSource ds = new DriverManagerDataSource();
	      ds.setDriverClassName(oracle.jdbc.driver.OracleDriver.class.getName());
	      ds.setUrl(cadenaConexionGestorBBDD);
	      ds.setUsername(usuarioGestorBBDD);
	      ds.setPassword(passwordGestorBBDD);
	      return ds;
	  }

}
