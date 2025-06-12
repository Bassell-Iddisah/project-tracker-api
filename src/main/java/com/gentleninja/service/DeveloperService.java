package com.gentleninja.service;

import com.gentleninja.dto.DeveloperDTO;
import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DeveloperService {
    // C
    DeveloperDTO createDeveloper(DeveloperDTO developerDTO);
    // R
    List<DeveloperDTO> getAllDevelopers();
    DeveloperDTO getDeveloperById(Integer id);
    Page<Developer> getDevelopers(Pageable pageable);
    // U
    DeveloperDTO updateDeveloper(Integer id, DeveloperDTO developerDTO);
    // D
    boolean deleteDeveloper(Integer id);
    // Others
    Map<DeveloperDTO, Set<TaskDTO>> assignTaskToDeveloper(Task task, DeveloperDTO dev);
}