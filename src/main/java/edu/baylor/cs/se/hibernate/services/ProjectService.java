package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.dto.ProjectDto;
import edu.baylor.cs.se.hibernate.model.Project;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    private static final Logger logger = Logger.getLogger(ProjectService.class);

    public Project save(ProjectDto projectDto)
    {

        List<Project> existingProjects = projectDao.getAllProjects();
        if (isDuplicateProjectKey(projectDto.getKey(),existingProjects)){
            logger.info("Duplicate project key");
            return null;
        }


        Project project = new Project();
        project.setKey(projectDto.getKey());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        projectDao.save(project);
        logger.info("New Project saved. Project Id: "+project.getId().toString());
        return project;
    }

    public Project getProjectByKey(String key){
        return projectDao.getProjectByKey(key);
    }

    public void delete(Long id){
        projectDao.delete(id);
        logger.info("Project with id: "+id.toString() + " deleted");
    }

    public void update(Project project) {
        projectDao.update(project);
        logger.info("Project with id: "+project.getId().toString() + " updated.");
    }

    public List<Project> getAllProjects(){
        return projectDao.getAllProjects();
    }

    public Project getProjectById(Long id){
        return projectDao.getProjectById(id);
    }

    public boolean isDuplicateProjectKey(String key, List<Project> projects){
        return projects.stream().map(Project::getKey).collect(Collectors.toList()).contains(key);

    }
}
