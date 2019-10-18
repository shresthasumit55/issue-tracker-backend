package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.RoomDao;
import edu.baylor.cs.se.hibernate.model.Course;
import edu.baylor.cs.se.hibernate.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Service
public class RoomService {

    @Autowired
    RoomDao roomDao;

    public void save(Room room) {
        roomDao.save(room);
    }

    public void delete(Long id){
        roomDao.delete(id);

    }

    public void update(Room room) {
        roomDao.update(room);
    }


}
