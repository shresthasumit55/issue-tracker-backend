package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.RoomDao;
import edu.baylor.cs.se.hibernate.model.ReferenceRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoomService {

    @Autowired
    RoomDao roomDao;

    public void save(ReferenceRoom room) {
        roomDao.save(room);
    }

    public void delete(Long id){
        roomDao.delete(id);

    }

    public void update(ReferenceRoom room) {
        roomDao.update(room);
    }


}
