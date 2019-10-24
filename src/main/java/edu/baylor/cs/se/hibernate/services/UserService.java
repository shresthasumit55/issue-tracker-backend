package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.UserDao;
import edu.baylor.cs.se.hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void save(User user) { userDao.save(user);}

    public void delete(Long id) { userDao.delete(id);}

    public void update(User user) { userDao.update(user);}

    public List<User> getAllUsers() { return userDao.getAllUsers();}

    public User getUserById(Long id) { return  userDao.getUserById(id);}

}
