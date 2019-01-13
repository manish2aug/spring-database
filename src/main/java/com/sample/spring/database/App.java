package com.sample.spring.database;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
//    	System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ctx.getEnvironment().setActiveProfiles("dev");
		ctx.register(ApplicationConfig.class);
		ctx.refresh();

		StudentService service = ctx.getBean(StudentService.class);
		service.getStudent();
		Thread.currentThread().sleep(10000);
		service.getStudent();
	}
}
