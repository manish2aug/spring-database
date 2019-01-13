package com.sample.spring.database;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.getEnvironment().setActiveProfiles("dev");
//		ctx.register(ApplicationConfig.class);
//		ctx.refresh();

    	System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		StudentService service = ctx.getBean(StudentService.class);
//		service.getAllStudent();
//		Thread.currentThread().sleep(10000);
//		service.getAllStudent();
		
		Student s = new Student("vansh", 10);
		Course c = new Course("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 5);
//		service.saveStudentandCourse(s,c);
		
		
//		service.saveStudentAndCourseInProgrammaticTransaction(s, c);
		service.saveStudentAndCourseInProgrammaticTransaction2(s, c);
	}
}
