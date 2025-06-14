package com.gentleninja.controller;

import com.gentleninja.dto.ProjectDTO;
import com.gentleninja.entity.Project;
import com.gentleninja.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO created = projectService.createProject(projectDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
