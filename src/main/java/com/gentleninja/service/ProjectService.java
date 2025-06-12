package com.gentleninja.service;

import com.gentleninja.dto.ProjectDTO;
import com.gentleninja.entity.Project;

import java.util.List;

public interface ProjectService {
    // C
    ProjectDTO createProject(ProjectDTO projectDTO);
    // R
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    // U
    Project updateProject(Long id, Project project);
    // D
    void deleteProject(Long id);
}
