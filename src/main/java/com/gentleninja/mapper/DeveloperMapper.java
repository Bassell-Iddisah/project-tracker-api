package com.gentleninja.mapper;

import com.gentleninja.entity.Developer;
import com.gentleninja.dto.DeveloperDTO;
import org.springframework.stereotype.Component;

@Component
public class DeveloperMapper {

    public DeveloperDTO toDTO(Developer developer) {
        if (developer == null) {
            return null;
        }
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(developer.getId());
        dto.setName(developer.getName());
        dto.setEmail(developer.getEmail());
        dto.setSkills(developer.getSkills());
        // Optionally map tasks if needed or keep tasks out for simplicity
        return dto;
    }

    public Developer toEntity(DeveloperDTO dto) {
        if (dto == null) {
            return null;
        }
        Developer developer = new Developer();
        developer.setId(dto.getId());
        developer.setName(dto.getName());
        developer.setEmail(dto.getEmail());
        developer.setSkills(dto.getSkills());
        // Handle tasks separately if needed
        return developer;
    }
}
