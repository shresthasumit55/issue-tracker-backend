package edu.baylor.cs.se.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserRoleMapping {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public UserRoleMapping(User user) {
        this.user = user;
    }

    public UserRoleMapping(User user, Role role, Project project) {
        this.user = user;
        this.role = role;
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleMapping that = (UserRoleMapping) o;
        return user.equals(that.user) &&
                role.equals(that.role) &&
                project.equals(that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role, project);
    }
}
