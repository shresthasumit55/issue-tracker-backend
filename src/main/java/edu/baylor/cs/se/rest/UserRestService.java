package edu.baylor.cs.se.rest;

import edu.baylor.cs.se.dto.LoginDto;
import edu.baylor.cs.se.dto.UserDto;
import edu.baylor.cs.se.exception.InsertFailureException;
import edu.baylor.cs.se.exception.UpdateFailureException;
import edu.baylor.cs.se.model.User;
import edu.baylor.cs.se.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is RestAPI for user related services
 */
@RestController
public class UserRestService {

    private UserService userService;

    @Autowired
    public UserRestService(UserService userService) {this.userService = userService;}


    @GetMapping("/")
    public String test(){
        return "your services are awake";
    }

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
        try{
            return new ResponseEntity(userService.save(user), HttpStatus.OK);
        }catch(InsertFailureException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUsers(@RequestBody User user){
        try {
            userService.update(user);
            return new ResponseEntity(HttpStatus.OK);
        }catch(UpdateFailureException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> authenticate(@RequestBody LoginDto loginDto){
        User user = userService.authenticate(loginDto);
        if (user ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> changePassword(@RequestBody LoginDto loginDto){
        User user = userService.changePassword(loginDto);
        if (user ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
