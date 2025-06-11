package com.gentleninja.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.gentleninja.entity.Developer;
import com.gentleninja.repository.DeveloperRepository;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperRepository devRepo;

    // Placeholder for list of developers
    private List<Developer> devs = new ArrayList<>(List.of(
            new Developer(1, "Bassell", "bbasssell16@gmail.com"),
            new Developer(2, "Sharon", "Yancy@gmail.com")
    ));

    // Get all Developers
    @GetMapping("/get")
    public List<Developer>getDevelopers() {
        return devs;
    }

    @PostMapping("/new")
    public ResponseEntity<List<Developer>> addDeveloper(@RequestBody Developer dev) {
        devs.add(dev);
        return ResponseEntity.ok(devRepo.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Developer> updateDeveloper(@RequestBody Developer dev) {
        Optional<Developer> devv = devRepo.findById(dev.getId());

        if (devv.isPresent()) {
            Developer updatedDev = devRepo.save(dev);
            return ResponseEntity.ok(updatedDev);
        } else {
        return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/del")
    public void deleteDeveloper(@RequestBody Developer dev) {
        if (devRepo.existsById(dev.getId())) {
            devRepo.deleteById(dev.getId());
        } else {
            return  Optional
        }
    }
}