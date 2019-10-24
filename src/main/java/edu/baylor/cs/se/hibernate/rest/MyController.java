package edu.baylor.cs.se.hibernate.rest;

import edu.baylor.cs.se.hibernate.model.ReferenceCourse;
import edu.baylor.cs.se.hibernate.model.ReferenceHero;
import edu.baylor.cs.se.hibernate.model.ReferenceRoom;
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
    public ResponseEntity<ReferenceCourse> getCoursesBySize(){
        return new ResponseEntity(superRepository.getCoursesBySize(),HttpStatus.OK);
    }

    @RequestMapping(value = "/courses2", method = RequestMethod.GET)
    public ResponseEntity<ReferenceCourse> getCoursesByStudentName(){
        return new ResponseEntity(superRepository.getCoursesByStudentName(),HttpStatus.OK);
    }
/*
    @PostMapping(value = "/save-room", produces = "application/json")
    public ReferenceRoom saveRoom(@RequestBody ReferenceRoom room) {
        roomService.save(room);
        return room;
    }


    @RequestMapping(value = "/update-room", method = RequestMethod.PUT)
    public ReferenceRoom updateRoom(@RequestBody ReferenceRoom room) {
        roomService.update(room);
        return room;
    }
    */

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteRoom(@PathVariable("id") Long id) {
        roomService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<List<ReferenceHero>> getHeroes(){
        return new ResponseEntity(heroService.getAllHeroes(),HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.POST)
    public ResponseEntity<ReferenceHero> fillHeroes(@RequestBody ReferenceHero hero){
        heroService.save(hero);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Please send a json array to fight the heroes
    @RequestMapping(value = "/heroes/fight", method = RequestMethod.POST)
    public ResponseEntity<ReferenceHero> fight(@RequestBody List<ReferenceHero> heroes){
        return new ResponseEntity(heroService.fight(heroes.get(0),heroes.get(1)),HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes/populate", method = RequestMethod.GET)
    public ResponseEntity populateHeroes(){
        heroService.populate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReferenceHero> getHeroById(@PathVariable("id") Long id){
        return new ResponseEntity(heroService.getHeroById(id),HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<List<ReferenceHero>> getHeroes(@RequestParam("name") String name,
                                                @RequestParam("sort") String asc,){
        return new ResponseEntity(heroService.getHeroesByCondition(name,asc),HttpStatus.OK);
    }
    */


}
