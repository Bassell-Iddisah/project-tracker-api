package com.gentleninja.mapper;

import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        if (task == null) return null;

        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(LocalDate.parse(task.getDueDate()))
                .status(task.getStatus())
                .project(task.getProject() != null ? task.getProject().getId() : null)
                .build();
    }

    public Task toEntity(TaskDTO dto) {
        if (dto == null) return null;

        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate().toString())
                .status(dto.getStatus())
                .build();
    }
}
