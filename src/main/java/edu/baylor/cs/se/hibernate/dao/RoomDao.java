package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoomDao {

    @PersistenceContext
    private EntityManager em;

    public Room getRoomById(Long id){
        return (Room) em.createQuery("SELECT c FROM Room c WHERE c.id=:id").setParameter("id",id).getSingleResult();
    }

    public void delete(Long id){
        Room room = getRoomById(id);
        em.remove(room);

    }

    public void save(Room room) {
        em.persist(room);
    }

    public void update(Room room){
        em.merge(room);
    }
}
