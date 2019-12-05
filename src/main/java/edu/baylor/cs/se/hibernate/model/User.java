package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstname;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    @Type(type="text")
    private String password;

    @Column
    private Boolean activeStatus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "PROJECT_USER",
            joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID") })
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Project> projectsInvolved = new HashSet<>();

    @Column(unique = true)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "must contain valid email address")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<UserRoleMapping> availableRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Issue> createdIssues = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignee")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Issue> assignedIssues = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Comment> postedComments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modifiedBy")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<ChangeTracker> issueModifications = new HashSet<>();


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
