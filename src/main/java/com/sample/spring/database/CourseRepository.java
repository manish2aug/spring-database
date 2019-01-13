package com.sample.spring.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CourseRepository {

	JdbcTemplate jdbcTemplate;

	@Autowired
	public CourseRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveCourse(Course c) {
		jdbcTemplate.execute(
				"INSERT INTO myschema.course(name, duration) VALUES ('" + c.getName() + "', " + c.getDuration() + ")");
	}
}
