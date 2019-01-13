package com.sample.spring.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value= {"app-dev.properties"})
@Profile("dev")
public class DevApplicationConfig {

	@Autowired
	Environment env;
	
	@Value("#{environment['java_home']}")
	private String javaHome;
	
	@Bean
	public DataSource dataSource() {
		System.out.println(javaHome);
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driver.name"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.userName"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}

}
