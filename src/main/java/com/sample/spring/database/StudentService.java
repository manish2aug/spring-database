package com.sample.spring.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;

	public void getAllStudent() {
		repository.getStudent();
	}

	public void saveStudentandCourse(Student s, Course c) {
		// TODO Auto-generated method stub
		repository.saveStudentAndCourse(s,c);
	}
	
	public void saveStudentAndCourseInProgrammaticTransaction(Student s, Course c){
		repository.saveStudentAndCourseInProgrammaticTransaction(s, c);
	}

	public void saveStudentAndCourseInProgrammaticTransaction2(Student s, Course c){
		repository.saveStudentAndCourseInProgrammaticTransaction2(s, c);
	}
}
