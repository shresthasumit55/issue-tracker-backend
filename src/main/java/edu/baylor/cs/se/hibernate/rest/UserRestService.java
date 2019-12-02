package edu.baylor.cs.se.hibernate.rest;

import edu.baylor.cs.se.hibernate.dto.UserDto;
import edu.baylor.cs.se.hibernate.model.User;
import edu.baylor.cs.se.hibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestService {

    private UserService userService;

    @Autowired
    public UserRestService(UserService userService) {this.userService = userService;}


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> postUsers(@RequestBody UserDto user){
        return new ResponseEntity(userService.save(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUsers(@RequestBody User user){
        userService.update(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUsers(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/editroles", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> editUserRoles(@RequestBody UserDto user){
        return new ResponseEntity(userService.updateUserRoles(user), HttpStatus.OK);
    }
}
