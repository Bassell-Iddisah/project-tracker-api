package com.gentleninja.service;

import com.gentleninja.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Integer id, Task task);
    void deleteTask(Integer id);
    Task getTaskById(Integer id);
    List<Task> getAllTasks();
    List<Task> getTasksByProjectId(Integer projectId);
    List<Task> getTasksByDeveloperId(Integer developerId);
    Task assignDevelopersToTask(Integer taskId, List<Integer> developerIds);
}
