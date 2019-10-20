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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<UserRoleMapping> availableRoles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private List<Issue> createdIssues;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assignee")
    private List<Issue> assignedIssues;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Comment> postedComments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "modifiedBy")
    private List<ChangeTracker> issueModifications;


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

    public List<UserRoleMapping> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(List<UserRoleMapping> availableRoles) {
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

    public List<Issue> getCreatedIssues() {
        return createdIssues;
    }

    public void setCreatedIssues(List<Issue> createdIssues) {
        this.createdIssues = createdIssues;
    }

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(List<Issue> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }

    public List<Comment> getPostedComments() {
        return postedComments;
    }

    public void setPostedComments(List<Comment> postedComments) {
        this.postedComments = postedComments;
    }

    public List<ChangeTracker> getIssueModifications() {
        return issueModifications;
    }

    public void setIssueModifications(List<ChangeTracker> issueModifications) {
        this.issueModifications = issueModifications;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
