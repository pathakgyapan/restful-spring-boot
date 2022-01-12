package net.javaguides.springboot.course;

//import net.javaguides.springboot.instructor.Instructor;
//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    //@ManyToOne
   // @JoinColumn(name = "instructor_id")
    //to map the foreign key column of a managed association
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //map property names with JSON keys during serializtion and deserialization
   // private Instructor instructor;
}

