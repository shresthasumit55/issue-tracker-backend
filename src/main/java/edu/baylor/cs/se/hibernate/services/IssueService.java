package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.IssueDao;
import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.dao.UserDao;
import edu.baylor.cs.se.hibernate.dto.IssueDto;
import edu.baylor.cs.se.hibernate.model.Issue;
import edu.baylor.cs.se.hibernate.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class IssueService {

    @Autowired
    IssueDao issueDao;


    public Issue save(IssueDto issueDto) {

        Issue issue = new Issue();
        issue.setName(issueDto.getName());
        issue.setIssueType(issueDto.getIssueType());
        issue.setPriorityLevel(issueDto.getPriorityLevel());
        issue.setCreatedDate(issueDto.getCreatedDate());
        issue.setDueDate(issueDto.getCreatedDate());
        issue.setLastModifiedDate(issueDto.getLastModifiedDate());
        issue.setStatus(issueDto.getStatus());


        issue.setProject(issueDto.getProject());
        issue.setCreator(issueDto.getCreator());
        issue.setAssignee(issueDto.getAssignee());
        issue.setComments(issueDto.getComments());
        issue.setTrackingHistory(issueDto.getTrackingHistory());

        issueDao.save(issue);
        return issue;

    }

    public void delete(Long id){
        issueDao.delete(id);
    }

    public void update(Issue issue) {
        issueDao.update(issue);
    }

    public List<Issue> getAllIssues(){
        return issueDao.getAllIssues();
    }

    public Issue getIssueById(Long id){
        return issueDao.getIssueById(id);
    }
}
