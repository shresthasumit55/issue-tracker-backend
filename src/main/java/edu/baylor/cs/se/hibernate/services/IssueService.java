package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.*;
import edu.baylor.cs.se.hibernate.dto.ChangeAssigneeDto;
import edu.baylor.cs.se.hibernate.dto.ChangeStatusDto;
import edu.baylor.cs.se.hibernate.dto.CommentDto;
import edu.baylor.cs.se.hibernate.dto.IssueDto;
import edu.baylor.cs.se.hibernate.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class IssueService {

    @Autowired
    IssueDao issueDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    ChangeTrackerDao changeTrackerDao;

    public Issue save(IssueDto issueDto) {

        try {
            Issue issue = new Issue();
            issue.setName(issueDto.getName());
            issue.setIssueType(IssueType.valueOf(issueDto.getType()));
            issue.setPriorityLevel(PriorityLevel.valueOf(issueDto.getPriorityLevel()));
            issue.setCreatedDate(new Date());
            issue.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse(issueDto.getDueDate()));
            issue.setLastModifiedDate(new Date());
            issue.setStatus(Status.NEW);
            issue.setDescription(issueDto.getDescription());

            Project project = projectDao.getProjectById(issueDto.getProject());
            issue.setProject(project);

            User creator = userDao.getUserById(issueDto.getCreator());
            issue.setCreator(creator);

            User assignee = userDao.getUserById(issueDto.getAssignee());
            issue.setAssignee(assignee);
            issueDao.save(issue);

            ChangeTracker changeTracker = new ChangeTracker();
            changeTracker.setIssue(issue);
            changeTracker.setChangeType(ChangeType.ISSUE_CREATION);
            changeTracker.setModifiedDate(new Date());
            changeTracker.setModifiedBy(creator);
            changeTrackerDao.save(changeTracker);
            return issue;
        }catch(ParseException e){
            System.out.println("Date cannot be parsed");
            return null;
        }

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

    public void changeAssignee(ChangeAssigneeDto changeAssigneeDto){
        User user = userDao.getUserById(changeAssigneeDto.getUserId());
        Issue issue = issueDao.getIssueById(changeAssigneeDto.getIssueId());
        User previousUser = issue.getAssignee();
        issue.setAssignee(user);
        issueDao.update(issue);

        User sessionUser = userDao.getUserById(changeAssigneeDto.getSessionUserId());

        ChangeTracker changeTracker = createChangeTracker(issue,ChangeType.REASSIGNMENT,sessionUser);
        changeTracker.setNewUser(user);
        changeTracker.setPreviousUser(previousUser);
        changeTrackerDao.save(changeTracker);
    }

    public void changeStatus(ChangeStatusDto changeStatusDto){
        Issue issue = issueDao.getIssueById(changeStatusDto.getIssueId());
        issue.setStatus(Status.valueOf(changeStatusDto.getStatus()));
        issueDao.update(issue);

        User sessionUser = userDao.getUserById(changeStatusDto.getSessionUserId());


        ChangeTracker changeTracker = createChangeTracker(issue,ChangeType.STATUS_CHANGE,sessionUser);
        changeTracker.setNewStatus(issue.getStatus());
        changeTrackerDao.save(changeTracker);
    }

    public void postComment(CommentDto commentDto){

        Issue issue = issueDao.getIssueById(commentDto.getIssueId());
        User user = userDao.getUserById(commentDto.getUserId());
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setIssue(issue);
        comment.setMessageText(commentDto.getComment());
        comment.setUser(user);
        commentDao.save(comment);

        ChangeTracker changeTracker = createChangeTracker(issue,ChangeType.COMMENT,user);
        changeTracker.setComment(comment);
        changeTrackerDao.save(changeTracker);

    }

    public List<Comment> getCommentsByIssue(Long issueId){
        return commentDao.getCommentsByIssue(issueId);
    }

    private ChangeTracker createChangeTracker(Issue issue,ChangeType changeType, User user){
        ChangeTracker changeTracker = new ChangeTracker();
        changeTracker.setIssue(issue);
        changeTracker.setChangeType(changeType);
        changeTracker.setModifiedDate(new Date());
        changeTracker.setModifiedBy(user);
        return changeTracker;
    }

}
