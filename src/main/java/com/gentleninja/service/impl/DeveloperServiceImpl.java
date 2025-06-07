package com.gentleninja.service.impl;

import com.gentleninja.dto.DeveloperDTO;
import com.gentleninja.dto.TaskDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.repository.DeveloperRepository;
import com.gentleninja.service.DeveloperService;
import com.gentleninja.utilities.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Override
    public DeveloperDTO getDeveloperById(Long id) {
        Developer developer = developerRepository.findById(id).orElse(null);
        return developer != null ? MapperUtil.toDeveloperDTO(developer) : null;
    }

    @Override
    public List<DeveloperDTO> getAllDevelopers() {
        List<Developer> developers = developerRepository.findAll();
        return developers.stream()
                .map(MapperUtil::toDeveloperDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksForDeveloper(Long developerId) {
        DeveloperDTO developer = getDeveloperById(developerId);
        Set<TaskDTO> tasks = developer.getTasks();
        return tasks.stream().toList();
    }

    @Override
    public Page<Developer> getDevelopers(Pageable pageable) {
        return developerRepository.findAll(pageable);
    }
}
