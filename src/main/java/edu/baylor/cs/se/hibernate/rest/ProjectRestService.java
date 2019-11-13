package edu.baylor.cs.se.hibernate.rest;

import edu.baylor.cs.se.hibernate.model.Project;
import edu.baylor.cs.se.hibernate.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectRestService {

    private ProjectService projectService;

    @Autowired
    public ProjectRestService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity(projectService.getAllProjects(), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id){
        return new ResponseEntity(projectService.getProjectById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Project> postProjects(@RequestBody Project project){
        projectService.save(project);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/update", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateProjects(@RequestBody Project project){
        projectService.update(project);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProjects(@PathVariable("id") Long id) {
        projectService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
