package edu.baylor.cs.se.rest;

import edu.baylor.cs.se.dto.ProjectDto;
import edu.baylor.cs.se.exception.InsertFailureException;
import edu.baylor.cs.se.exception.UpdateFailureException;
import edu.baylor.cs.se.model.Project;
import edu.baylor.cs.se.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This class is RestAPI for project related services
 */
@RestController
public class ProjectRestService {

    private ProjectService projectService;

    @Autowired
    public ProjectRestService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity(projectService.getAllProjects(), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id){
        return new ResponseEntity(projectService.getProjectById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Project> postProjects(@RequestBody ProjectDto project){
        try {
            return new ResponseEntity(projectService.save(project), HttpStatus.OK);
        }catch(InsertFailureException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/projects/update", method = RequestMethod.PUT)
    public ResponseEntity<Project> updateProjects(@RequestBody Project project){
        try {
            projectService.update(project);
            return new ResponseEntity(HttpStatus.OK);
        }catch(UpdateFailureException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/projects/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProjects(@PathVariable("id") Long id) {
        projectService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
