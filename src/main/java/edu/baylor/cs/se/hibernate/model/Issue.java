package edu.baylor.cs.se.hibernate.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class Issue {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated
    private IssueType issueType;

    @Enumerated
    private PriorityLevel priorityLevel;

    @Column
    private Date createdDate;

    @Column
    private Date dueDate;

    @Column
    private Date lastModifiedDate;

    @Enumerated
    private Status status;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User assignee;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
    private List<Comment> comments;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "issue")
    private Set<ChangeTracker> trackingHistory;

    public Issue() {
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

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<ChangeTracker> getTrackingHistory() {
        return trackingHistory;
    }

    public void setTrackingHistory(Set<ChangeTracker> trackingHistory) {
        this.trackingHistory = trackingHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
