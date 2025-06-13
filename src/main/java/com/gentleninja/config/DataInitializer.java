package com.gentleninja.config;

import com.gentleninja.entity.Role;
import com.gentleninja.entity.RoleType;
import com.gentleninja.repository.RoleRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        Arrays.stream(RoleType.values()).forEach(roleType -> {
            roleRepository.findByName(roleType)
                    .orElseGet(() -> roleRepository.save(new Role(roleType)));
        });
//        System.out.println("Saved roles: " + roleRepository.findAll());
    }
}
