package net.javaguides.springboot.student;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@ManytoMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = student_id),)
	
}
