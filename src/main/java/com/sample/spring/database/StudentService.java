package com.sample.spring.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;

	public void getStudent() {
		List<Student> q = repository.jdbcTemplate.query("select * from spring.\"Student\"", new RowMapper<Student>() {
			
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s = new Student();
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				return s;
			}
		});
		
		System.out.println(q.get(0).getName());
	}
}
