package com.gentleninja.service;

import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Project;
import com.gentleninja.entity.Task;
import com.gentleninja.exceptions.ResourceNotFoundException;
import com.gentleninja.repository.DeveloperRepository;
import com.gentleninja.repository.ProjectRepository;
import com.gentleninja.repository.TaskRepository;
import com.gentleninja.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Task updateTask(Integer id, Task updatedTask) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        existing.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(existing);
    }

    @Override
    public void deleteTask(Integer id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByProjectId(Integer projectId) {
        Project project = projectRepository.findById(Long.valueOf(projectId))
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
    public Task assignDevelopersToTask(Integer taskId, List<Integer> developerIds) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        Set<Developer> developers = new HashSet<>(developerRepository.findAllById((Iterable) developerIds));
        task.getDevelopers().addAll(developers);
        return taskRepository.save(task);
    }
}
