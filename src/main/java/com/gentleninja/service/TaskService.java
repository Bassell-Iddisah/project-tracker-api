package com.gentleninja.service;

import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    Task getTaskById(Long id);
    Page<Task> getAllTasks(Pageable pageable);
    List<Task> getTasksByProjectId(Long projectId);
    List<Task> getTasksByDeveloperId(Long developerId);
    Task assignDevelopersToTask(Long taskId, List<Long> developerIds);
    List<Developer> getDevelopersByTaskId(Long taskId);
}
