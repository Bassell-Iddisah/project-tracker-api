package com.gentleninja.service;

import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;
import java.util.List;

public interface TaskService {
    // C
    Task createTask(Task task);
    // R
    Task getTaskById(Integer id);
    List<Task> getTasksByProjectId(Integer projectId);
    List<Task> getTasksByDeveloperId(Integer developerId);
    List<Developer> getDevelopersByTaskId(Integer taskId);
    List<TaskDTO> getTasksByStatus(String status);
    List<Task> getAllTasks();
    // U
    Task updateTask(Integer id, Task task);
    // D
    void deleteTask(Integer id);
    // Other
    Task assignDevelopersToTask(Integer taskId, List<Integer> developerIds);
}