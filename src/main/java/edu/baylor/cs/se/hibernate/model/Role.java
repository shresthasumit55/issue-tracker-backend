package edu.baylor.cs.se.hibernate.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserRoleMapping> availableRoles;


    public Role() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id) &&
                name.equals(role.name) &&
                description.equals(role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserRoleMapping> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(List<UserRoleMapping> availableRoles) {
        this.availableRoles = availableRoles;
    }
}
