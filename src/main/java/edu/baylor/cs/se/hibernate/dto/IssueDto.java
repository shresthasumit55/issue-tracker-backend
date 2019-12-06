package edu.baylor.cs.se.hibernate.dto;

import java.util.Date;


/**
 * This class stores data related to issue from UI
 */
public class IssueDto {

    private Long id;

    private String name;

    private String type;

    private String priorityLevel;

    private Date createdDate;

    private String dueDate;

    private Date lastModifiedDate;

    private String status;

    private Long project;

    private Long creator;

    private Long assignee;

    private String description;


    public IssueDto(){

    }

    public IssueDto(String name, String type, String priorityLevel, String dueDate, Long project, Long creator, Long assignee, String description) {
        this.name = name;
        this.type = type;
        this.priorityLevel = priorityLevel;
        this.dueDate = dueDate;
        this.project = project;
        this.creator = creator;
        this.assignee = assignee;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getAssignee() {
        return assignee;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
