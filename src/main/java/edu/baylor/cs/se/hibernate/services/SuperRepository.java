package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.ReferenceCourse;
import edu.baylor.cs.se.hibernate.model.ReferenceRoom;
import edu.baylor.cs.se.hibernate.model.ReferenceStudent;
import edu.baylor.cs.se.hibernate.model.ReferenceTeacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Spring annotations, feel free to ignore it
@Repository
@Transactional
public class SuperRepository {

    @PersistenceContext
    private EntityManager em;

    public void populate(){
        ReferenceStudent student = createStudent("Joe");
        ReferenceStudent student2 = createStudent("John");
        ReferenceStudent student3 = createStudent("Bob");
        ReferenceStudent student4 = createStudent("Tim");
        ReferenceStudent student5 = createStudent("Jimmy");


        ReferenceTeacher teacher = new ReferenceTeacher();
        teacher.setFirstName("Bob");
        teacher.setLastName("Porter");
        teacher.setEmail("bob@porter.com");
        //teacher.setTelephoneNumber("213123"); throws error due to length violation
        //teacher.setTelephoneNumber("abcdefghij"); throws error because only numbers are allowed
        teacher.setTelephoneNumber("1234567890"); //this passes validation
        em.persist(teacher);

        ReferenceCourse course = new ReferenceCourse();
        course.setName("Software engineering");
        course.setTeacher(teacher);
        course.getStudents().add(student);
        course.getStudents().add(student3);
        course.getStudents().add(student4);
        em.persist(course);

        ReferenceCourse course2 = new ReferenceCourse();
        course2.setName("Boring class");
        course2.setTeacher(teacher);
        course2.getStudents().add(student);
        course2.getStudents().add(student5);
        em.persist(course2);

        //Do you know why this is not working?
        student2.getCourses().add(course);

        ReferenceRoom room = new ReferenceRoom();
        room.setLocation("Cashion 308");
        course.setAssignedRoom(room);
        em.persist(room);

        ReferenceRoom room2 = new ReferenceRoom();
        room2.setLocation("Cashion 306");
        course2.setAssignedRoom(room2);
        em.persist(room2);

    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<ReferenceCourse> getCoursesBySize(){
        return em.createQuery("SELECT c FROM ReferenceCourse c WHERE c.students.size > 2 ").getResultList();
    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<ReferenceCourse> getCoursesByStudentName(){
        return em.createNamedQuery("ReferenceCourse.findCoursesByStudentName").setParameter("name","Jimmy").getResultList();
    }

    private ReferenceStudent createStudent(String name){
        ReferenceStudent student = new ReferenceStudent();
        student.setName(name);
        em.persist(student);
        return student;
    }
}
