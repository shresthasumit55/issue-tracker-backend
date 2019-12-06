package edu.baylor.cs.se.hibernate.dto;



/**
 * This class stores data related to status change request from UI
 */
public class ChangeStatusDto {

    private Long issueId;

    private String status;

    private Long sessionUserId;

    public ChangeStatusDto() {
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(Long sessionUserId) {
        this.sessionUserId = sessionUserId;
    }
}
