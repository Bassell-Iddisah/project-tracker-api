package com.gentleninja.service;

import com.gentleninja.entity.Project;
import com.gentleninja.repository.ProjectRepository;
import com.gentleninja.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        existing.setName(project.getName());
        existing.setDescription(project.getDescription());
        existing.setDeadline(project.getDeadline());
        existing.setStatus(project.getStatus());
        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
