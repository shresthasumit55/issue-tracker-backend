package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.ChangeTracker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ChangeTrackerDao {

    @PersistenceContext
    private EntityManager em;

    public void save(ChangeTracker changeTracker) {
        em.persist(changeTracker);
    }

    public List<ChangeTracker> getChangeTrackerByIssueId(Long issueId){

        String hql = "Select i from ChangeTracker i where i.issue.id = :issueId";
        Query query = em.createQuery(hql);
        query.setParameter("issueId",issueId);
        return (List<ChangeTracker>) query.getResultList();

    }

    public List<ChangeTracker> getChangeList(){
        return (List<ChangeTracker>) em.createQuery("SELECT i FROM ChangeTracker i").getResultList();
    }

}
