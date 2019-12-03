package edu.baylor.cs.se.hibernate.dto;

import org.springframework.web.multipart.MultipartFile;

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
