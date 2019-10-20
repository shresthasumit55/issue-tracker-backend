package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ReferenceStudent implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    @ManyToMany/*(mappedBy = "students"/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY*/
    //annotation bellow is just for Jackson serialization in rest
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID") })
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<ReferenceCourse> courses = new HashSet();

    public Set<ReferenceCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<ReferenceCourse> courses) {
        this.courses = courses;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
