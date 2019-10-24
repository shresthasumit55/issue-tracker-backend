package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.Project;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProjectDao {

    @PersistenceContext
    private EntityManager em;

    public Project getProjectById(Long id){
        return em.find(Project.class,id);
    }

    public List<Project> getAllProjects(){

        return (List<Project>) em.createQuery("SELECT i FROM Project i").getResultList();
    }

    public void delete(Long id){
        Project project = getProjectById(id);
        em.remove(project);

    }

    public void save(Project project) {
        em.persist(project);
    }

    public void update(Project project){
        em.merge(project);
    }

}
