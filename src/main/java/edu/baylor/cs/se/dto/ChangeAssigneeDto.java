package edu.baylor.cs.se.dto;

/**
 * This class stores data related to assignee change request from UI
 */
public class ChangeAssigneeDto {

    private Long userId;

    private Long issueId;

    private Long sessionUserId;

    public ChangeAssigneeDto() {
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

    public Long getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(Long sessionUserId) {
        this.sessionUserId = sessionUserId;
    }
}
