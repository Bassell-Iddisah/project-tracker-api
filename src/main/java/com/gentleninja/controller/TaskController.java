package com.gentleninja.controller;

import com.gentleninja.dto.DeveloperAssignmentRequest;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;
import com.gentleninja.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "status") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Task> taskPage = taskService.getAllTasks(pageable); // Ensure this method exists and returns Page<Task>

        return ResponseEntity.ok(taskPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{taskId}/developers")
    public ResponseEntity<List<Developer>> getDevelopersByTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getDevelopersByTaskId(taskId));
    }

    @PreAuthorize("@accessChecker.isTaskOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Integer id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/assign-developers")
    public ResponseEntity<Task> assignDevelopersToTask(
            @PathVariable Integer taskId,
            @RequestBody DeveloperAssignmentRequest request) {

        Task updatedTask = taskService.assignDevelopersToTask(taskId, request.getDeveloperIds());
        return ResponseEntity.ok(updatedTask);
    }
}
