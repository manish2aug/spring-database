package com.sample.spring.database;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"com.sample.spring.database"})
@EnableCaching
public class ApplicationConfig {
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("student");
	}
}
