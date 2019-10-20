package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String key;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "PROJECT_USER",
            joinColumns = { @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") })
    private Set<User> members;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<UserRoleMapping> availableRoles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    private List<Issue> issues;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public List<UserRoleMapping> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(List<UserRoleMapping> availableRoles) {
        this.availableRoles = availableRoles;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return key.equals(project.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
