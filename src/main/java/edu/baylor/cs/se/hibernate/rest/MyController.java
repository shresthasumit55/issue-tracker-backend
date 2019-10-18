package edu.baylor.cs.se.hibernate.rest;

import edu.baylor.cs.se.hibernate.model.Course;
import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.model.Room;
import edu.baylor.cs.se.hibernate.services.HeroService;
import edu.baylor.cs.se.hibernate.services.RoomService;
import edu.baylor.cs.se.hibernate.services.SuperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MyController {

    private SuperRepository superRepository;

    private RoomService roomService;

    private HeroService heroService;

    //you should generally favour constructor/setter injection over field injection
    @Autowired
    public MyController(SuperRepository superRepository, RoomService roomService, HeroService heroService){
        this.superRepository = superRepository;
        this.roomService = roomService;
        this.heroService = heroService;
    }

    //very bad practise - using GET method to insert something to DB
    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity populate(){
        superRepository.populate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<Course> getCoursesBySize(){
        return new ResponseEntity(superRepository.getCoursesBySize(),HttpStatus.OK);
    }

    @RequestMapping(value = "/courses2", method = RequestMethod.GET)
    public ResponseEntity<Course> getCoursesByStudentName(){
        return new ResponseEntity(superRepository.getCoursesByStudentName(),HttpStatus.OK);
    }

    @PostMapping(value = "/save-room", produces = "application/json")
    public Room saveRoom(@RequestBody Room room) {
        roomService.save(room);
        return room;
    }

    @RequestMapping(value = "/update-room", method = RequestMethod.PUT)
    public Room updateRoom(@RequestBody Room room) {
        roomService.update(room);
        return room;
    }
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteRoom(@PathVariable("id") Long id) {
        roomService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> getHeroes(){
        return new ResponseEntity(heroService.getAllHeroes(),HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.POST)
    public ResponseEntity<Hero> fillHeroes(@RequestBody Hero hero){
        heroService.save(hero);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Please send a json array to fight the heroes
    @RequestMapping(value = "/heroes/fight", method = RequestMethod.POST)
    public ResponseEntity<Hero> fight(@RequestBody List<Hero> heroes){
        return new ResponseEntity(heroService.fight(heroes.get(0),heroes.get(1)),HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes/populate", method = RequestMethod.GET)
    public ResponseEntity populateHeroes(){
        heroService.populate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") Long id){
        return new ResponseEntity(heroService.getHeroById(id),HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> getHeroes(@RequestParam("name") String name,
                                                @RequestParam("sort") String asc,){
        return new ResponseEntity(heroService.getHeroesByCondition(name,asc),HttpStatus.OK);
    }
    */


}
