package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.IssueDao;
import edu.baylor.cs.se.hibernate.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class IssueService {

    @Autowired
    IssueDao issueDao;

    public void save(Issue issue) {
        issueDao.save(issue);
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
