package com.gentleninja.dto;

import java.util.List;
import java.util.Set;

public class DeveloperDTO {
    private Long id;
    private String name;
    private String email;
    private Set<TaskDTO> tasks;
    private List<String> skills;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<TaskDTO> getTasks() { return tasks; }
    public void setTasks(Set<TaskDTO> tasks) { this.tasks = tasks; }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
