package com.gentleninja.mapper;

import com.gentleninja.dto.ProjectDTO;
import com.gentleninja.entity.Project;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ProjectMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ProjectDTO toDTO(Project project) {
        if (project == null) return null;

        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .deadline(project.getDeadline() != null ? project.getDeadline() : null)
                .status(project.getStatus())
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
        if (dto == null) return null;

        System.out.println("Parsed deadline: " + LocalDate.parse(dto.getDeadline(), FORMATTER));

        return Project.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .deadline(dto.getDeadline() != null ? dto.getDeadline() : null)
                .status(dto.getStatus())
                .build();
    }


}
