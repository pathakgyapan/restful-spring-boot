package net.javaguides.springboot;

import net.javaguides.springboot.userDetail.UserDetail;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity //class can be mapped to a table
@Getter
@Setter //enable you avoid having to write manually getter and setter methods in java classes
@SQLDelete(sql = "UPDATE user SET enabled = 0 WHERE id = ?")
@Where(clause = "(enabled=1)")
@FilterDef(
	    name = "nameEmailFilter",
	    parameters = {
	        @ParamDef(name = "name", type = "string"),
	        @ParamDef(name = "email", type = "string"),
	    }
	)
	@Filter(
	    name = "nameEmailFilter",
	    condition = "(fullname like :name or email like :email)"
	)
	@Filter(
	    name = "nameEmailFilter",
	    condition = "(fullname like :name or email like :email)"
	)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullname;

    private String email;

    private String address;
    
    @Column(columnDefinition = "boolean default true")
    private boolean enabled;


    @OneToOne(optional = true,cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    
    @JoinColumn(name = "user_detail_id")
    //maps the foreign key column of a managed association
    private UserDetail userDetail;
}
