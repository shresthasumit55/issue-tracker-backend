package edu.baylor.cs.se.dto;


/**
 * This class stores data related to project from UI
 */
public class ProjectDto {

    private Long id;

    private String key_id;

    private String name;


    private String description;


    public ProjectDto() {
    }

    public ProjectDto(String key_id, String name, String description) {
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
}
