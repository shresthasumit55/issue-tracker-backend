package edu.baylor.cs.se.hibernate.dto;

public class ChangeAssigneeDto {

    private Long userId;

    private Long issueId;

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
}
