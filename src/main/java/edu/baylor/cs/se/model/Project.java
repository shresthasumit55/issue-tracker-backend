package edu.baylor.cs.se.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Data Model for Project, that maps the class to the project table
 * Details the relationship of project with other models
 */

@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String key_id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @ManyToMany(mappedBy = "projectsInvolved", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<User> members = new HashSet<User>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<UserRoleMapping> availableRoles = new HashSet<>();

    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private Set<Issue> issues = new HashSet<>();

    public Project() {
    }

    public Project(@NotNull String key_id, @NotNull String name, @NotNull String description) {
        this.key_id = key_id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key_id;
    }

    public void setKey(String key_id) {
        this.key_id = key_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public Set<UserRoleMapping> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(Set<UserRoleMapping> availableRoles) {
        this.availableRoles = availableRoles;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return key_id.equals(project.key_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key_id);
    }
}
