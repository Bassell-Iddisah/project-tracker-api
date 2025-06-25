package com.gentleninja.mapper;

import com.gentleninja.dto.UserDTO;
import com.gentleninja.entity.Role;
import com.gentleninja.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName().toString())  // Convert RoleType to String
                .collect(Collectors.toSet());

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(roleNames)
                .build();
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .Name(dto.getName())
                .email(dto.getEmail())
                // password is excluded intentionally
                .build();
    }
}
