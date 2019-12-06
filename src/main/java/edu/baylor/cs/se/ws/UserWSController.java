package edu.baylor.cs.se.ws;

import edu.baylor.cs.se.dto.UserDto;
import edu.baylor.cs.se.exception.InsertFailureException;
import edu.baylor.cs.se.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserWSController {


    @Autowired
    UserService userService;

    /*@Scheduled(fixedRate = 5000)
    @MessageMapping("/statistics")
    @SendTo("/issues/stats/")
    public String greeting() throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("called");
        return "Hello";
    }*/

    @MessageMapping("/statistics")
    @SendTo("/topic/stats")
    public String postUsers(@RequestBody UserDto userDto){
        try {
            userService.save(userDto);
            return userDto.getEmail();
        }catch(InsertFailureException e){
            return null;
        }
    }
}

