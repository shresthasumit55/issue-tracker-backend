package edu.baylor.cs.se.dao;

import edu.baylor.cs.se.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * This class handles database communication for User table
 */
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public User getUserById(Long id) {return em.find(User.class,id);}

    public List<User> getAllUsers(){
        return (List<User>) em.createQuery("SELECT i FROM User i ").getResultList();
    }

    public void delete(Long id){
        User user = getUserById(id);
        em.remove(user);
    }

    public void save(User user) { em.persist(user);}

    public void update(User user) {em.merge(user);}

    public User getUserByEmail(String email){
        return (User) em.createQuery("SELECT i FROM User i where i.email=:email").setParameter("email",email).getResultList().get(0);
    }

}
