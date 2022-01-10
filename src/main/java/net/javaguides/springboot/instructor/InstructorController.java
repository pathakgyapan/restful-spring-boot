package net.javaguides.springboot.instructor;

import net.javaguides.springboot.Exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InstructorController
 */
@RestController// creates restful controllers.
@RequestMapping("instructor")
//maps HTTP requests to handler methods of MVC and REST controllers
public class InstructorController {

    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("{id}")
    //handle get type of request method
    public ResponseEntity<Instructor> getInstructor(
        @PathVariable(value = "id") long id
        //handle template variables in the request URI mapping, and set them as method parameters
        
    ) {
        return new ResponseEntity<>(
            instructorRepository
                .findById(id)
                .orElseThrow(ItemNotFoundException::new),
            HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Instructor> postInstructor(
        @RequestBody Instructor instructor
    ) {
        // var courses = instructor
        //     .getCourses()
        //     .stream()
        //     .map(course -> {
        //         course.setInstructor(instructor);
        //         return course;
        //     })
        //     .collect(Collectors.toList());

        // instructor.setCourses(courses);

        return new ResponseEntity<>(
            instructorRepository.save(instructor),
            HttpStatus.OK
        );
    }
}
