package com.sample.spring.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
public class StudentRepository {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	CourseRepository repo;

	@Autowired
	PlatformTransactionManager transactionManager;
	
	private final TransactionTemplate transactionTemplate;
	
	@Autowired
	public StudentRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.transactionTemplate = new TransactionTemplate(transactionManager);
	}

	@Cacheable("student")
	@CacheEvict("student")
	public void getStudent() {
		System.out.println("service called");
		List<Student> q = jdbcTemplate.query("select * from myschema.student", new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student s = new Student();
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				return s;
			}
		});

		if (q.size() > 0) {
			System.out.println(q.get(0).getName());
		}
		System.out.println("No student found");
	}

	@Transactional
	public void saveStudentAndCourse(Student s, Course c) {
		jdbcTemplate.execute("INSERT INTO myschema.student(name, age) VALUES ('"+s.getName()+"', "+s.getAge()+")");
		try {
			repo.saveCourse(c);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public void saveStudentAndCourseInProgrammaticTransaction(Student s, Course c) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				try {
					jdbcTemplate.execute("INSERT INTO myschema.student(name, age) VALUES ('"+s.getName()+"', "+s.getAge()+")");
					repo.saveCourse(c);
				}catch (Exception e) {
					System.out.println("exception");
					status.setRollbackOnly();
				}
			}
		});
	}
	
	public void saveStudentAndCourseInProgrammaticTransaction2(Student s, Course c) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// explicitly setting the transaction name is something that can be done only programmatically
		def.setName("SomeTxName");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			jdbcTemplate.execute("INSERT INTO myschema.student(name, age) VALUES ('"+s.getName()+"', "+s.getAge()+")");
			repo.saveCourse(c);
		}
		catch (Exception ex) {
			System.out.println("exception saveStudentAndCourseInProgrammaticTransaction2");
			transactionManager.rollback(status);
		    throw ex;
		}
		transactionManager.commit(status);
	}
}
