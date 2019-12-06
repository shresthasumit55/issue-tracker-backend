package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.dto.ProjectDto;
import edu.baylor.cs.se.hibernate.exception.InsertFailureException;
import edu.baylor.cs.se.hibernate.exception.UpdateFailureException;
import edu.baylor.cs.se.hibernate.model.Project;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Component for Project that includes the business logic
 * To Perform CRUD operation in Project
 */
@Transactional
@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    private static final Logger logger = Logger.getLogger(ProjectService.class);

    /**
     * Method to save the Project
     * It checks that the key of the new Project is unique compared to the existing projects
     * @param projectDto
     * @return Project
     * @throws InsertFailureException
     */
    public Project save(ProjectDto projectDto) throws InsertFailureException  {
        try {

            List<Project> existingProjects = projectDao.getAllProjects();
            if (isDuplicateProjectKey(projectDto.getKey(), existingProjects)) {
                logger.info("Duplicate project key");
                return null;
            }

            Project project = new Project();
            project.setKey(projectDto.getKey());
            project.setName(projectDto.getName());
            project.setDescription(projectDto.getDescription());
            projectDao.save(project);
            logger.info("New Project saved. Project Id: " + project.getId().toString());
            return project;
        }catch(Exception e){
            e.printStackTrace();
            throw new InsertFailureException("Project could not be saved");
        }
    }

    /**
     * Method to get a particular project based on the key provided
     * @param key
     * @return Project
     */
    public Project getProjectByKey(String key){
        try {
            return projectDao.getProjectByKey(key);
        }catch(Exception e){
            logger.error("Could not fetch project for key: "+key);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to delete the project based on the ID provided
     * @param id
     */
    public void delete(Long id){
        try {
            projectDao.delete(id);
            logger.info("Project with id: " + id.toString() + " deleted");
        }catch(Exception e){
            logger.error("Could not delete project");
            e.printStackTrace();
        }
    }

    /**
     * Method to update the Project
     * @param project
     * @throws UpdateFailureException
     */
    public void update(Project project) throws UpdateFailureException{
        try {
            projectDao.update(project);
            logger.info("Project with id: " + project.getId().toString() + " updated.");
        }catch(Exception e){
            logger.error("Could not update project");
            e.printStackTrace();
            throw new UpdateFailureException("Could not update project");
        }
    }

    /**
     * Method to get the List of all the Projects
     * @return List of all Projects
     */
    public List<Project> getAllProjects(){
        try {
            return projectDao.getAllProjects();
        }catch(Exception e){
            logger.error("Could not fetch projects");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to get a particular project based on its id
     * @param id
     * @return Project
     */
    public Project getProjectById(Long id){
        try {
            return projectDao.getProjectById(id);
        }catch(Exception e){
            logger.error("Could not fetch projects");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to check whether a key provided is already used as the key of existing project
     * This is to ensure that two projects do not have the same key
     * @param key
     * @param projects
     * @return True or False based on if the key already exists
     */
    public boolean isDuplicateProjectKey(String key, List<Project> projects){
        return projects.stream().map(Project::getKey).collect(Collectors.toList()).contains(key);

    }
}
