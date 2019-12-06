package edu.baylor.cs.se;

import edu.baylor.cs.se.dao.ProjectDao;
import edu.baylor.cs.se.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectDaoTests {

    @Mock
    private ProjectDao projectDao;

    @Autowired
    public MockMvc mockMvc;

    Project p1;

    Project p2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        p1 = new Project("PR1","Project 1","Project number one");
        p2 = new Project("PR2","Project 2","Project number two");
        when(projectDao.getAllProjects()).thenReturn(Arrays.asList(p1, p2));
        //mockMvc = standaloneSetup(projectRestService).build();

    }


    @Test
    public void checkInsertedProjectExists(){
        Project project = projectDao.getProjectByKey("PR1");
        assertThat(project.getName()=="Project 1");
    }
}
