package com.gentleninja.service;

import com.gentleninja.dto.DeveloperDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeveloperService {
    List<DeveloperDTO> getAllDevelopers();
    DeveloperDTO getDeveloperById(Long id);
    Page<Developer> getDevelopers(Pageable pageable);
}