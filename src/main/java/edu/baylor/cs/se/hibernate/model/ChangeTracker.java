package edu.baylor.cs.se.hibernate.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChangeTracker {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date modifiedDate;

    @Enumerated
    private ChangeType changeType;

    @ManyToOne
    private Issue issue;

    @ManyToOne
    private User modifiedBy;

    public ChangeTracker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


}


