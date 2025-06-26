package com.gentleninja.service.impl;

import com.gentleninja.dto.DeveloperDTO;
import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;
import com.gentleninja.mapper.TaskMapper;
import com.gentleninja.repository.DeveloperRepository;
import com.gentleninja.repository.TaskRepository;
import com.gentleninja.service.DeveloperService;
import com.gentleninja.service.TaskService;
import com.gentleninja.utilities.MapperUtil;
import com.gentleninja.mapper.DeveloperMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final TaskService taskService;
    private final DeveloperMapper developerMapper;

    @Override
    public DeveloperDTO createDeveloper(DeveloperDTO developerDTO) {
        Developer developer = developerMapper.toEntity(developerDTO);
        if (developer != null){
            Developer savedDeveloper = developerRepository.save(developer);
        }
        return developerMapper.toDTO(savedDeveloper);
    }

    @Override
    public List<DeveloperDTO> getAllDevelopers() {
        List<Developer> developers = developerRepository.findAll();
        return developers.stream()
                .map(MapperUtil::toDeveloperDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DeveloperDTO getDeveloperById(Integer id) {
        Developer developer = developerRepository.findById(id).orElse(null);
        return developer != null ? MapperUtil.toDeveloperDTO(developer) : null;
    }

    @Override
    public Page<Developer> getDevelopers(Pageable pageable) {
        return developerRepository.findAll(pageable);
    }

    @Override
    public DeveloperDTO updateDeveloper(Integer id, DeveloperDTO developerDTO) {
        // Fetch existing developer
        Developer existingDeveloper = developerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found with ID: " + id));

        // Update fields
        existingDeveloper.setName(developerDTO.getName());
        existingDeveloper.setEmail(developerDTO.getEmail());
        existingDeveloper.setSkills(developerDTO.getSkills());
//        existingDeveloper.setAvailability(developerDTO.getAvailability());

        // Save updated developer
        Developer updatedDeveloper = developerRepository.save(existingDeveloper);

        // Return updated DTO
        return developerMapper.toDTO(updatedDeveloper);
    }

    @Override
    public boolean deleteDeveloper(Integer id) {
        Optional<Developer> optionalDeveloper = developerRepository.findById(id);

        if (optionalDeveloper.isPresent()) {
            developerRepository.delete(optionalDeveloper.get());
            return true;
        } else {
            return false;
        }
    }

    public Map<DeveloperDTO, Set<TaskDTO>> assignTaskToDeveloper(Task task, DeveloperDTO devDTO) {
        Developer developer = developerRepository.findById(Integer.parseInt(String.valueOf(devDTO.getId())))
                .orElseThrow(() -> new RuntimeException("Developer not found with ID: " + Integer.parseInt(String.valueOf(devDTO.getId()))));

        Task taskToAssign = taskService.getTaskById(Integer.parseInt(String.valueOf(task.getId())));

        // Add the task to the developer
        developer.getTasks().add(taskToAssign);
        developerRepository.save(developer);

        // Map to DTOs
        DeveloperDTO developerDTO = developerMapper.toDTO(developer);
        Set<TaskDTO> taskDTOs = developer.getTasks().stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toSet());

        Map<DeveloperDTO, Set<TaskDTO>> assignedTask = new HashMap<>();
        assignedTask.put(developerDTO, taskDTOs);

        return assignedTask;
    }

}