package net.javaguides.springboot;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.userDetail.UserDetail;

@Entity
//class can be mapped to a table
@Getter
@Setter
//enable you avoid having to write manually getter and setter methods in java classes
public class User {

    @Id
    //specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //provides the specification of generation strategies for the primary keys
    private int id;

    private String fullname;

    private String email;

    private String address;
    
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name ="user_detail_id")
    //maps the foreign key column of a managed association
     private UserDetail userDetail;
    
    
}
