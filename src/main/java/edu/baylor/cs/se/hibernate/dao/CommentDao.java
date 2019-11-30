package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.Comment;
import edu.baylor.cs.se.hibernate.model.Issue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public List<Comment> getCommentsByIssue(Long issueId){

        String hql = "Select i from Comment i where i.issue.id = :issueId";
        Query query = em.createQuery(hql);
        query.setParameter("issueId",issueId);
        return (List<Comment>) query.getResultList();
    }
}
