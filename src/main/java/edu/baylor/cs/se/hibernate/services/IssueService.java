package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.CommentDao;
import edu.baylor.cs.se.hibernate.dao.IssueDao;
import edu.baylor.cs.se.hibernate.dao.UserDao;
import edu.baylor.cs.se.hibernate.dto.ChangeAssigneeDto;
import edu.baylor.cs.se.hibernate.dto.ChangeStatusDto;
import edu.baylor.cs.se.hibernate.dto.CommentDto;
import edu.baylor.cs.se.hibernate.dto.IssueDto;
import edu.baylor.cs.se.hibernate.model.Comment;
import edu.baylor.cs.se.hibernate.model.Issue;
import edu.baylor.cs.se.hibernate.model.Status;
import edu.baylor.cs.se.hibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public Issue save(IssueDto issueDto) {

        Issue issue = new Issue();
        issue.setName(issueDto.getName());
        issue.setIssueType(issueDto.getIssueType());
        issue.setPriorityLevel(issueDto.getPriorityLevel());
        issue.setCreatedDate(issueDto.getCreatedDate());
        issue.setDueDate(issueDto.getCreatedDate());
        issue.setLastModifiedDate(issueDto.getLastModifiedDate());
        issue.setStatus(issueDto.getStatus());
        issue.setDescription(issueDto.getDescription());


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

    public void changeAssignee(ChangeAssigneeDto changeAssigneeDto){
        User user = userDao.getUserById(changeAssigneeDto.getUserId());
        Issue issue = issueDao.getIssueById(changeAssigneeDto.getIssueId());
        issue.setAssignee(user);
        issueDao.update(issue);
    }

    public void changeStatus(ChangeStatusDto changeStatusDto){
        Issue issue = issueDao.getIssueById(changeStatusDto.getIssueId());
        issue.setStatus(Status.valueOf(changeStatusDto.getStatus()));
        issueDao.update(issue);
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
    }

    public List<Comment> getCommentsByIssue(Long issueId){
        return commentDao.getCommentsByIssue(issueId);
    }


}
