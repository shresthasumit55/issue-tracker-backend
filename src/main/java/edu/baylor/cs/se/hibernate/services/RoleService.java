package edu.baylor.cs.se.hibernate.services;


import edu.baylor.cs.se.hibernate.dao.RoleDao;
import edu.baylor.cs.se.hibernate.model.Issue;
import edu.baylor.cs.se.hibernate.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public void save(Role role) {
        roleDao.save(role);
    }

    public void delete(Long id){
        roleDao.delete(id);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public List<Role> getAllRoles(){
        return roleDao.getAllRoles();
    }

    public Role getRoleById(Long id){
        return roleDao.getRoleById(id);
    }
}
