package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.UserDao;
import edu.baylor.cs.se.hibernate.model.ChangeTracker;
import edu.baylor.cs.se.hibernate.model.Issue;
import edu.baylor.cs.se.hibernate.model.Project;
import edu.baylor.cs.se.hibernate.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class ChangeTrackerService {


    @Autowired
    UserDao userDao;

    private static final Logger logger = Logger.getLogger(ChangeTrackerService.class);

    public Set<ChangeTracker> getChangesByUser(Long userId){
        try {
            Set<ChangeTracker> result = new HashSet<>();
            User user = userDao.getUserById(userId);
            Set<Project> projects = user.getProjectsInvolved();
            for (Project project : projects) {
                Set<Issue> issues = project.getIssues();
                for (Issue issue : issues) {
                    result.addAll(issue.getTrackingHistory());
                }
            }
            return result;
        }catch(Exception e){
            logger.error("Could not fetch change tracker for user: "+userId);
            return null;
        }
    }
}
