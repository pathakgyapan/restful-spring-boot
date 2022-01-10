package net.javaguides.springboot.instructor;

import net.javaguides.springboot.course.Course;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity//class can be mapped to a table. When you created a new entity you have to do annotated with @entity

@Data// generates getters for all fields, its a boilerplate associated with POJOS(PLAIN OLD JAVA OBJECTS)
@AllArgsConstructor//generates constructor with 1 parameter for each field in your class
@NoArgsConstructor// generates constructor with no parameters
public class Instructor {

    @Id//specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //configure the way of increment of the specified field
    private long id;

    private String name;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    //courses haru delete or update bhayo pani cascade huncha
    private List<Course> courses = new ArrayList<>();

    public void setCourses(List<Course> courses) {
        this.courses = courses;

        for (Course course : courses) {
            course.setInstructor(this);
        }
    }
}



