package com.gentleninja.service;

import com.gentleninja.dto.ProjectDTO;
import com.gentleninja.entity.Project;

import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    Project updateProject(Long id, Project project);
    void deleteProject(Long id);
    Project getProjectById(Long id);
    List<Project> getAllProjects();
}
