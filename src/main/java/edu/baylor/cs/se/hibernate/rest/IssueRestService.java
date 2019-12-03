package edu.baylor.cs.se.hibernate.rest;

import edu.baylor.cs.se.hibernate.dto.ChangeAssigneeDto;
import edu.baylor.cs.se.hibernate.dto.ChangeStatusDto;
import edu.baylor.cs.se.hibernate.dto.CommentDto;
import edu.baylor.cs.se.hibernate.dto.IssueDto;
import edu.baylor.cs.se.hibernate.model.Comment;
import edu.baylor.cs.se.hibernate.model.Issue;
import edu.baylor.cs.se.hibernate.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class IssueRestService {

    private IssueService issueService;

    @Autowired
    public IssueRestService(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public ResponseEntity<List<Issue>> getAllIssues(){
        return new ResponseEntity(issueService.getAllIssues(), HttpStatus.OK);
    }

    @RequestMapping(value = "/issues/{id}", method = RequestMethod.GET)
    public ResponseEntity<Issue> getIssueById(@PathVariable("id") Long id){
        return new ResponseEntity(issueService.getIssueById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/issues", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Issue> postIssues(@RequestBody IssueDto issue){
        return new ResponseEntity(issueService.save(issue), HttpStatus.OK);
    }

    @RequestMapping(value = "/issues/update", method = RequestMethod.PUT)
    public ResponseEntity<Issue> updateIssues(@RequestBody Issue issue){
        issueService.update(issue);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/issues/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteIssues(@PathVariable("id") Long id) {
        issueService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/change-assignee", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity changeAssignee(@RequestBody ChangeAssigneeDto changeAssigneeDto){
        issueService.changeAssignee(changeAssigneeDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/change-status", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity changeAssignee(@RequestBody ChangeStatusDto changeStatusDto){
        issueService.changeStatus(changeStatusDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity postComments(@RequestPart ("issueId") @Valid String issueId, @RequestPart ("comment") @Valid String comment,
                                       @RequestPart ("userId") @Valid String userId, @RequestPart(required = false) MultipartFile attach){
        issueService.postComment(new CommentDto(Long.valueOf(userId),Long.valueOf(issueId),comment),attach);
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity postComments(@RequestPart ("commentDto") @Valid CommentDto commentDto, @RequestPart("attach")MultipartFile file){
        issueService.postComment(commentDto);
        return new ResponseEntity(HttpStatus.OK);
    }*/

    @RequestMapping(value = "/issue/comment/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getCommentsForIssue(@PathVariable("id") Long issueId){
        return new ResponseEntity(issueService.getCommentsByIssue(issueId), HttpStatus.OK);
    }

    @RequestMapping(value = "/attach/comment/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getAttachForComment(@PathVariable("id") Long commentId){
        Comment comment = issueService.getComment(commentId);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(comment.getContentType()));
        header.setContentLength(comment.getAttachment().length);
        header.set("Content-Disposition", "attachment; filename=" + comment.getFileName());

        return new ResponseEntity<>(comment.getAttachment(), header, HttpStatus.OK);
    }
}
