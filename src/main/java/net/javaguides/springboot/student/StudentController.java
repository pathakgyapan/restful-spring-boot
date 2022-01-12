package net.javaguides.springboot.student;


	import net.javaguides.springboot.Exception.ItemNotFoundException;
	import javax.transaction.Transactional;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import lombok.var;

	/**
	 * StudentController
	 */
	@RestController
	@RequestMapping("student")
	@Transactional
	public class StudentController {

	    private final StudentRepository studentRepository;

	    public StudentController(StudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	    }

	    @GetMapping("{id}")
	    public ResponseEntity<Student> getInstructor(
	        @PathVariable(value = "id") long id
	    ) {
	        var instructor = studentRepository
	            .findById(id)
	            .orElseThrow(ItemNotFoundException::new);
	        return new ResponseEntity<>(instructor, HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<Student> postInstructor(
	        @RequestBody Student student
	    ) {
	        return new ResponseEntity<>(
	            studentRepository.save(student),
	            HttpStatus.OK
	        );
	    }
	}


