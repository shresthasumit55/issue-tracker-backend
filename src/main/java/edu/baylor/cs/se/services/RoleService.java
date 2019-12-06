package edu.baylor.cs.se.services;


import edu.baylor.cs.se.dao.RoleDao;
import edu.baylor.cs.se.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Component for Role
 * TO perform CRUD on Role
 */
@Transactional
@Service

public class RoleService {
    @Autowired
    RoleDao roleDao;


    private static final Logger logger = Logger.getLogger(RoleService.class);

    /**
     * Method to save a new Role
     * @param role, new role to be saved
     */
    public void save(Role role){
        try {
            roleDao.save(role);
            logger.info("New Project saved. Project Id: " + role.getId().toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to delete an existing Role based on the ID provided
     * @param id, id of the role that needs to be deleted
     */
    public void delete(Long id){
        try {
            roleDao.delete(id);
            logger.info("Role with id: " + id.toString() + " deleted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to update an existing Role
     * @param role, role that needs to be updated
     */
    public void update(Role role){
        try{
            roleDao.update(role);
            logger.info("Role with id: "+role.getId().toString() + " updated.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Method to get the list of all existing roles
     * @return List of Roles
     */
    public List<Role> getAllRoles(){
        return roleDao.getAllRoles();
    }

    /**
     * Method to get a particular Role based on the id provided
     * @param id, id of a particular role
     * @return Role
     */
    public Role getRoleById(Long id){
        return roleDao.getRoleById(id);
    }


}
