package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//Obsolete with Java 8 @Repeatable annotation
@NamedQueries({
        @NamedQuery(
                name = "ReferenceCourse.findCoursesByStudentName",
                query = "SELECT c FROM ReferenceCourse c JOIN c.students s WHERE s.name = :name"
        )
})
@Entity
public class ReferenceCourse {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    //annotation bellow is just for Jackson serialization in rest
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private ReferenceTeacher teacher;

    @ManyToMany//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in rest
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<ReferenceStudent> students = new HashSet();

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private ReferenceRoom assignedRoom;

    public void setStudents(Set<ReferenceStudent> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReferenceTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(ReferenceTeacher teacher) {
        this.teacher = teacher;
    }

    public Set<ReferenceStudent> getStudents() {
        return students;
    }

    public ReferenceRoom getAssignedRoom() {
        return assignedRoom;
    }

    public void setAssignedRoom(ReferenceRoom assignedRoom) {
        this.assignedRoom = assignedRoom;
    }
}
