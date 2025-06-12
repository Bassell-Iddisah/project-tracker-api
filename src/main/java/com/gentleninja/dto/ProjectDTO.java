package com.gentleninja.dto;

import lombok.*;
import java.time.LocalDate;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public record ProjectDTO(
        Long id,
        String name,
        String description,
        String deadline,
        String status
) {
    private Long id;
    private String name;
    private String description;
    private String deadline;
    private String status;
}
