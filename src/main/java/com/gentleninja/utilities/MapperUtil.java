package com.gentleninja.utilities;

import com.gentleninja.dto.DeveloperDTO;
import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperUtil {

    public static DeveloperDTO toDeveloperDTO(Developer developer) {
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(Long.valueOf(developer.getId()));
        dto.setName(developer.getName());
        dto.setEmail(developer.getEmail());

        if (developer.getTasks() != null) {
            Set<TaskDTO> taskDTOs = developer.getTasks().stream()
                    .map(MapperUtil::toTaskDTO)
                    .collect(Collectors.toSet());
            dto.setTasks(taskDTOs);
        }
        return dto;
    }

    public static TaskDTO toTaskDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(LocalDate.parse(task.getDueDate()));
        return dto;
    }
}
