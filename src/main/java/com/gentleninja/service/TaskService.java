package com.gentleninja.service;

import com.gentleninja.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    List<Task> getTasksByProjectId(Long projectId);
    List<Task> getTasksByDeveloperId(Long developerId);
    Task assignDevelopersToTask(Long taskId, List<Long> developerIds);
}
