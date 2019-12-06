package edu.baylor.cs.se.hibernate.rest;

import edu.baylor.cs.se.hibernate.model.Role;
import edu.baylor.cs.se.hibernate.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This class is RestAPI for role related services
 */
@RestController
public class RoleRestService {


    private RoleService roleService;

    @Autowired
    public RoleRestService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity(roleService.getAllRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleyId(@PathVariable("id") Long id){
        return new ResponseEntity(roleService.getRoleById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ResponseEntity<Role> postRoles(@RequestBody Role role){
        roleService.save(role);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/roles/update", method = RequestMethod.PUT)
    public ResponseEntity<Role> updateRoles(@RequestBody Role role){
        roleService.update(role);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/roles/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRoles(@PathVariable("id") Long id) {
        roleService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}


