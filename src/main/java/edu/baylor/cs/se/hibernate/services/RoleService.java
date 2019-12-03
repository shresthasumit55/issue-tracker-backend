package edu.baylor.cs.se.hibernate.services;


import edu.baylor.cs.se.hibernate.dao.RoleDao;
import edu.baylor.cs.se.hibernate.model.Issue;
import edu.baylor.cs.se.hibernate.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    private static final Logger logger = Logger.getLogger(RoleService.class);

    public void save(Role role)
    {
        roleDao.save(role);
        logger.info("New Project saved. Project Id: "+role.getId().toString());
    }

    public void delete(Long id)
    {
        roleDao.delete(id);
        logger.info("Role with id: "+id.toString() + " deleted");
    }

    public void update(Role role)
    {
        roleDao.update(role);
        logger.info("Role with id: "+role.getId().toString() + " updated.");
    }

    public List<Role> getAllRoles(){
        return roleDao.getAllRoles();
    }

    public Role getRoleById(Long id){
        return roleDao.getRoleById(id);
    }
}
