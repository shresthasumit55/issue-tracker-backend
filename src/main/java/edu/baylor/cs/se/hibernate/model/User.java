package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstname;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Project> projectsInvolved;

    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "must contain valid email address")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserRoleMapping> availableRoles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Issue> createdIssues;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assignee")
    private Set<Issue> assignedIssues;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Comment> postedComments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "modifiedBy")
    private Set<ChangeTracker> issueModifications;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Project> getProjectsInvolved() {
        return projectsInvolved;
    }

    public void setProjectsInvolved(Set<Project> projectsInvolved) {
        this.projectsInvolved = projectsInvolved;
    }

    public Set<UserRoleMapping> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(Set<UserRoleMapping> availableRoles) {
        this.availableRoles = availableRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return firstname.equals(user.firstname) &&
                middleName.equals(user.middleName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email);
    }

    public Set<Issue> getCreatedIssues() {
        return createdIssues;
    }

    public void setCreatedIssues(Set<Issue> createdIssues) {
        this.createdIssues = createdIssues;
    }

    public Set<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(Set<Issue> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }

    public Set<Comment> getPostedComments() {
        return postedComments;
    }

    public void setPostedComments(Set<Comment> postedComments) {
        this.postedComments = postedComments;
    }

    public Set<ChangeTracker> getIssueModifications() {
        return issueModifications;
    }

    public void setIssueModifications(Set<ChangeTracker> issueModifications) {
        this.issueModifications = issueModifications;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
