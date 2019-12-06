package edu.baylor.cs.se;

import edu.baylor.cs.se.dto.CommentDto;
import edu.baylor.cs.se.dto.IssueDto;
import edu.baylor.cs.se.exception.InsertFailureException;
import edu.baylor.cs.se.exception.UpdateFailureException;
import edu.baylor.cs.se.model.*;
import edu.baylor.cs.se.services.IssueService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IssueServiceTests {

    @Autowired
    IssueService issueService;

    @Test
    public void checkInsertedIssueExists(){
        try {
            issueService.save(new IssueDto("Issue1","Bug","LOW","2019-12-12",Long.valueOf(1),Long.valueOf(1),Long.valueOf(2),"issue number 1"));
            List<Issue> issues = issueService.getAllIssues();
            Assert.assertTrue(issues.size()>1);
        }catch(InsertFailureException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void checkIssueUpdates(){
        try {
            issueService.save(new IssueDto("Issue1","Bug","LOW","2019-12-12",Long.valueOf(1),Long.valueOf(1),Long.valueOf(2),"issue number 1"));
            issueService.save(new IssueDto("Issue2","Bug","LOW","2019-12-12",Long.valueOf(1),Long.valueOf(1),Long.valueOf(2),"issue number 2"));
            List<Issue> issues = issueService.getAllIssues();

            Issue firstIssue = issues.get(0);
            Long id = firstIssue.getId();
            String newDescription = "Modified";
            firstIssue.setDescription(newDescription);
            issueService.update(firstIssue);

            Issue updatedIssue = issueService.getIssueById(id);
            Assert.assertTrue(updatedIssue.getDescription().equals(newDescription));
        }catch(UpdateFailureException e){
            e.printStackTrace();
        }catch(InsertFailureException i){
            i.printStackTrace();
        }
    }

    @Test
    public void checkInsertedComments(){
            Long issueId = Long.valueOf(1);
            CommentDto commentDto = new CommentDto(Long.valueOf(1),issueId,"This is a comment");
            issueService.postComment(commentDto,null);

            List<Comment> comments = issueService.getCommentsByIssue(issueId);
            Assert.assertTrue(comments.size()!=0);
    }


    @Test
    public void checkCreateChangeTracker(){

        User user = new User();
        user.setEmail("test@test.com");
        ChangeTracker changeTracker = issueService.createChangeTracker(new Issue(), ChangeType.COMMENT,user);
        Assert.assertEquals(changeTracker.getModifiedBy().getEmail(),user.getEmail());


    }
}
