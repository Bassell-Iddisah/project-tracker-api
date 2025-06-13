package com.gentleninja.service.impl;

import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Project;
import com.gentleninja.entity.Task;
import com.gentleninja.exceptions.ResourceNotFoundException;
import com.gentleninja.mapper.TaskMapper;
import com.gentleninja.repository.DeveloperRepository;
import com.gentleninja.repository.ProjectRepository;
import com.gentleninja.repository.TaskRepository;
import com.gentleninja.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final DeveloperRepository developerRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }

    @Override
    public List<Task> getTasksByProjectId(Integer projectId) {
        Project project = projectRepository.findById(Long.parseLong(String.valueOf(projectId)))
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));
        return project.getTasks().stream().toList();
    }

    @Override
    public List<Task> getTasksByDeveloperId(Integer developerId) {
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new EntityNotFoundException("Developer not found with id: " + developerId));
        return developer.getTasks().stream().toList();
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public List<Developer> getDevelopersByTaskId(Integer taskId) {
        Task task = taskRepository.findById(Long.parseLong(String.valueOf(taskId)))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        return new ArrayList<>(task.getDevelopers());
    }

    @Override
    public List<TaskDTO> getTasksByStatus(String status) {
        List<Task> tasks = taskRepository.findByStatus(status.toUpperCase());
        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Task updateTask(Integer id, Task updatedTask) {
        Task existing = taskRepository.findById(Long.parseLong(String.valueOf(id)))
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        existing.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(existing);
    }

    @Override
    public void deleteTask(Integer id) {
        if (!taskRepository.existsById(Long.parseLong(String.valueOf(id)))) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(Long.parseLong(String.valueOf(id)));
    }

    @Override
    public Task assignDevelopersToTask(Integer taskId, List<Integer> developerIds) {
        Task task = taskRepository.findById(Long.parseLong(String.valueOf(taskId)))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        Set<Developer> developers = new HashSet<>(developerRepository.findAllById(developerIds));
        task.getDevelopers().addAll(developers);
        return taskRepository.save(task);
    }
}
