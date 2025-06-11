package com.gentleninja.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperDTO {
    private Long id;
    private String name;
    private String email;
    private Set<TaskDTO> tasks;
    private List<String> skills;
}
