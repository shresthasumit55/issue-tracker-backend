package edu.baylor.cs.se.dto;


/**
 * This class stores data related to comment addition request from UI
 */
public class CommentDto {

    private Long userId;

    private Long issueId;

    private String comment;

    public CommentDto(Long userId, Long issueId, String comment) {
        this.userId = userId;
        this.issueId = issueId;
        this.comment = comment;
    }

    public CommentDto() {
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
