package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.Issue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * This class handles database communication for Issue table
 */
@Repository
public class IssueDao {

    @PersistenceContext
    private EntityManager em;

    public Issue getIssueById(Long id){
        return em.find(Issue.class,id);
    }

    public List<Issue> getAllIssues(){
        return (List<Issue>) em.createQuery("SELECT i FROM Issue i").getResultList();
    }


    public void delete(Long id){
        Issue issue = getIssueById(id);
        em.remove(issue);
    }

    public void save(Issue issue) {
        em.persist(issue);
    }

    public void update(Issue issue){
        em.merge(issue);
    }

}
