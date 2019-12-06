package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.*;
import edu.baylor.cs.se.hibernate.dto.ChangeAssigneeDto;
import edu.baylor.cs.se.hibernate.dto.ChangeStatusDto;
import edu.baylor.cs.se.hibernate.dto.CommentDto;
import edu.baylor.cs.se.hibernate.dto.IssueDto;
import edu.baylor.cs.se.hibernate.exception.InsertFailureException;
import edu.baylor.cs.se.hibernate.exception.NotAManagerException;
import edu.baylor.cs.se.hibernate.exception.UpdateFailureException;
import edu.baylor.cs.se.hibernate.model.*;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private static final org.apache.log4j.Logger logger = Logger.getLogger(IssueService.class);

    public Issue save(IssueDto issueDto) throws InsertFailureException{

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
            logger.info("New Issue saved. Issue Id: "+issue.getId().toString());


            ChangeTracker changeTracker = new ChangeTracker();
            changeTracker.setIssue(issue);
            changeTracker.setChangeType(ChangeType.ISSUE_CREATION);
            changeTracker.setModifiedDate(new Date());
            changeTracker.setModifiedBy(creator);
            changeTrackerDao.save(changeTracker);
            logger.info("Change save in Change Tracker for Issue Id: "+issue.getId().toString());

            return issue;
        }catch(ParseException e){
            logger.error("Date cannot be parsed");
            e.printStackTrace();
            return null;
        }catch(Exception e){
            e.printStackTrace();
            throw new InsertFailureException("Issue cannot be saved");
        }

    }

    public void delete(Long id){
        try {
            issueDao.delete(id);
            logger.info("Issue with id: " + id.toString() + " deleted");
        }catch(Exception e){
            logger.error("Cannot perform delete");
            e.printStackTrace();
        }
    }

    public void update(Issue issue) throws UpdateFailureException{
        try {
            issueDao.update(issue);
            logger.info("Issue with id: " + issue.getId().toString() + " updated.");
        }catch(Exception e){
            e.printStackTrace();
            throw new UpdateFailureException("Cannot update issue");
        }
    }

    public List<Issue> getAllIssues(){
        try {
            return issueDao.getAllIssues();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<ChangeTracker> getChangeList(){
        try {
            return changeTrackerDao.getChangeList();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Issue getIssueById(Long id){
        try {
            return issueDao.getIssueById(id);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void changeAssignee(ChangeAssigneeDto changeAssigneeDto){
        try {
            User user = userDao.getUserById(changeAssigneeDto.getUserId());
            Issue issue = issueDao.getIssueById(changeAssigneeDto.getIssueId());
            User previousUser = issue.getAssignee();
            issue.setAssignee(user);
            issueDao.update(issue);

            logger.info("Assignee updated for Issue with id: " + issue.getId().toString());

            User sessionUser = userDao.getUserById(changeAssigneeDto.getSessionUserId());

            ChangeTracker changeTracker = createChangeTracker(issue, ChangeType.REASSIGNMENT, sessionUser);
            changeTracker.setNewUser(user);
            changeTracker.setPreviousUser(previousUser);
            changeTrackerDao.save(changeTracker);
            logger.info("Assignee update logged in change tracker for IssueID: " + issue.getId().toString());
        }catch (Exception e){
            logger.error("Assignee could not be changed");
            e.printStackTrace();

        }

    }

    public void changeStatus(ChangeStatusDto changeStatusDto) throws NotAManagerException {
        try {
            Issue issue = issueDao.getIssueById(changeStatusDto.getIssueId());
            User sessionUser = userDao.getUserById(changeStatusDto.getSessionUserId());

            if (!checkifUserisProjectManager(sessionUser,issue,changeStatusDto.getStatus())){
                logger.error("The user cannot resolve the issue.");
                throw new NotAManagerException("The user is not a project manager");
            }

            issue.setStatus(Status.valueOf(changeStatusDto.getStatus()));
            issueDao.update(issue);

            logger.info("Status updated for Issue with id: " + issue.getId().toString());



            ChangeTracker changeTracker = createChangeTracker(issue, ChangeType.STATUS_CHANGE, sessionUser);
            changeTracker.setNewStatus(issue.getStatus());
            changeTrackerDao.save(changeTracker);
            logger.info("Status update logged in change tracker for IssueID: " + issue.getId().toString());
        }catch(Exception e){
            logger.error("Issue Status could not be changed");
            e.printStackTrace();
        }

    }

    public boolean checkifUserisProjectManager(User sessionUser, Issue issue, String newStatus){
        if (!newStatus.equals(Status.RESOLVED.toString())){
            //for other issue status, project manager check need not be done.
            return true;
        }
        List<UserRoleMapping> userRoles = sessionUser.getAvailableRoles().stream().filter(item -> item.getProject()
                .getId()==issue.getProject().getId()).collect(Collectors.toList());
        for (UserRoleMapping userRoleMapping: userRoles){
            if (userRoleMapping.getRole().getName().equals("MGR")){
                return true;
            }
        }
        return false;


    }

    public void postComment(CommentDto commentDto, MultipartFile attach){
        try{
            Issue issue = issueDao.getIssueById(commentDto.getIssueId());
            User user = userDao.getUserById(commentDto.getUserId());
            Comment comment = new Comment();
            comment.setDate(new Date());
            comment.setIssue(issue);
            comment.setMessageText(commentDto.getComment());
            comment.setUser(user);
            if (attach != null) {
                comment.setAttachment(Base64.encodeBase64(attach.getBytes()));
                comment.setContentType(attach.getContentType());
                comment.setFileName(attach.getOriginalFilename());


            }
        commentDao.save(comment);
        logger.info("Comment added for Issue with id: "+issue.getId().toString() );

        ChangeTracker changeTracker = createChangeTracker(issue,ChangeType.COMMENT,user);
        changeTracker.setComment(comment);
        changeTrackerDao.save(changeTracker);
            logger.info("Comment addition logged in change tracker for IssueID: "+issue.getId().toString());
        }catch (IOException e) {
           logger.error("Cannot encode attachment");
        }

    }

    public List<Comment> getCommentsByIssue(Long issueId){
        try {
            return commentDao.getCommentsByIssue(issueId);
        }catch(Exception e){
            logger.error("Comments could not fetched for issueId:"+issueId);
            e.printStackTrace();
            return null;
        }
    }

    public Comment getComment(Long commentId){
        try {
            return commentDao.getCommentById(commentId);
        }catch (Exception e){
            logger.error("Could not fetch comments");
            e.printStackTrace();
            return null;
        }
    }

    public ChangeTracker createChangeTracker(Issue issue,ChangeType changeType, User user){
        ChangeTracker changeTracker = new ChangeTracker();
        changeTracker.setIssue(issue);
        changeTracker.setChangeType(changeType);
        changeTracker.setModifiedDate(new Date());
        changeTracker.setModifiedBy(user);
        return changeTracker;
    }

}
