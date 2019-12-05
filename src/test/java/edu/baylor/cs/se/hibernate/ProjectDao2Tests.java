package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.dto.ProjectDto;
import edu.baylor.cs.se.hibernate.model.Project;
import edu.baylor.cs.se.hibernate.services.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProjectDao2Tests {

    @Autowired
    ProjectService projectService;

    @Test
    public void checkInsertedProjectExists(){
        projectService.save(new ProjectDto("TR55","Test Project 1","Test Project number one"));
        Project project = projectService.getProjectByKey("TR1");
        assertThat(project.getName().equals("Test Project 1"));
    }

    @Test
    public void checkDuplicateProjectKey() {
        Project p1 = new Project("KEY1", "Project key 1", "Awesome project");
        Project p2 = new Project("KEY2", "Project key 2", "Awesome project 2");

        List<Project> existingProjects = new ArrayList<>();
        existingProjects.add(p1);
        existingProjects.add(p2);

        Project p3 = new Project("KEY2", "Project 3", "Awesome project 3");
        assertThat(projectService.isDuplicateProjectKey(p3.getKey(),existingProjects));
    }














}
