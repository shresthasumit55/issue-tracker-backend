package edu.baylor.cs.se.hibernate.dao;


import edu.baylor.cs.se.hibernate.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * This class handles database communication for Role table
 */
@Repository
public class RoleDao {
    @PersistenceContext
    private EntityManager em;

    public Role getRoleById(Long id){
        return em.find(Role.class,id);
    }


    public List<Role> getAllRoles(){

        return (List<Role>) em.createQuery("SELECT i FROM Role i").getResultList();
    }

    public void delete(Long id){
        Role role = getRoleById(id);
        em.remove(role);

    }

    public void save(Role role) {
        em.persist(role);
    }

    public void update(Role role){
        em.merge(role);
    }

}
