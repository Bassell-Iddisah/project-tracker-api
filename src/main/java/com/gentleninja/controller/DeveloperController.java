package com.gentleninja.controller;

import com.gentleninja.dto.DeveloperDTO;
import com.gentleninja.entity.Developer;
import com.gentleninja.service.DeveloperService;
import com.gentleninja.utilities.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gentleninja.mapper.DeveloperMapper;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;
    private final DeveloperMapper developerMapper;


    public DeveloperController(DeveloperService developerService, DeveloperMapper developerMapper) {
        this.developerService = developerService;
        this.developerMapper = developerMapper;
    }

    @PostMapping
    public ResponseEntity<DeveloperDTO> createDeveloper(@RequestBody DeveloperDTO developerDTO) {
        DeveloperDTO createdDeveloper = developerService.createDeveloper(developerDTO);
        return ResponseEntity.ok(createdDeveloper);
    }

    @GetMapping
    public ResponseEntity<Page<DeveloperDTO>> getAllDevelopers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Developer> developerPage = developerService.getDevelopers(pageable);

        Page<DeveloperDTO> dtoPage = developerPage.map(developerMapper::toDTO);

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getDeveloperById(@PathVariable Integer id) {
        DeveloperDTO developerDTO = developerService.getDeveloperById(id);
        if (developerDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(developerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(@PathVariable Integer id, @RequestBody DeveloperDTO developerDTO) {
        DeveloperDTO updatedDeveloper = developerService.updateDeveloper(id, developerDTO);
        if (updatedDeveloper == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDeveloper);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Integer id) {
        boolean deleted = developerService.deleteDeveloper(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
