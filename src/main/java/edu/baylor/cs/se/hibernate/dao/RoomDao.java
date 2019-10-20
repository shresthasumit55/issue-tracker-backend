package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.ReferenceRoom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoomDao {

    @PersistenceContext
    private EntityManager em;

    public ReferenceRoom getRoomById(Long id){
        return (ReferenceRoom) em.createQuery("SELECT c FROM ReferenceRoom c WHERE c.id=:id").setParameter("id",id).getSingleResult();
    }

    public void delete(Long id){
        ReferenceRoom room = getRoomById(id);
        em.remove(room);

    }

    public void save(ReferenceRoom room) {
        em.persist(room);
    }

    public void update(ReferenceRoom room){
        em.merge(room);
    }
}
