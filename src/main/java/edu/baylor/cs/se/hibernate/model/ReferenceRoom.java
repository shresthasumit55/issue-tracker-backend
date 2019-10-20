package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ReferenceRoom implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String location;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignedRoom")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<ReferenceCourse> associatedCourses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<ReferenceCourse> getAssociatedCourses() {
        return associatedCourses;
    }

    public void setAssociatedCourses(Set<ReferenceCourse> associatedCourses) {
        this.associatedCourses = associatedCourses;
    }
}