package com.sample.spring.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value= {"app-dev.properties"})
@Profile("dev")
public class DevApplicationConfig {

	@Bean
	public DataSource dataSource(
			@Value("${database.driver.name}") String driverName,
			@Value("${database.url}") String url,
			@Value("${database.userName}") String userName,
			@Value("${databse.password}") String password
			) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

}
