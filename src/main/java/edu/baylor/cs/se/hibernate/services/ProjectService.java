package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    public void save(Project project) {
        projectDao.save(project);
    }

    public void delete(Long id){
        projectDao.delete(id);
    }

    public void update(Project project) {
        projectDao.update(project);
    }

    public List<Project> getAllProjects(){
        return projectDao.getAllProjects();
    }

    public Project getProjectById(Long id){
        return projectDao.getProjectById(id);
    }
}
