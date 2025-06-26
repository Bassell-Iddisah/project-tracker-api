package com.gentleninja.service;

import com.gentleninja.dto.UserDTO;
import com.gentleninja.entity.Task;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<Task> getTasksByUserId(Long userId);
}
