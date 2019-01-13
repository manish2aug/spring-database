package com.sample.spring.database;

public class Course {
	private long id;

	public Course(String name, int duration) {
		super();
		this.name = name;
		this.duration = duration;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	private String name;
	private int duration;
}
