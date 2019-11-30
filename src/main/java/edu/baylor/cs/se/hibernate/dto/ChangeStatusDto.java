package edu.baylor.cs.se.hibernate.dto;

public class ChangeStatusDto {

    private Long issueId;

    private String status;

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
}
