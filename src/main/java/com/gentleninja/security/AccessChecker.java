package com.gentleninja.security;

import com.gentleninja.entity.Task;
import com.gentleninja.repository.TaskRepository;
import com.gentleninja.utilities.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("accessChecker")
public class AccessChecker {

    @Autowired
    private TaskRepository taskRepo;

    public boolean isTaskOwner(Long taskId) {
        String currentUsername = SecurityUtil.getCurrentUserUsername();
        Optional<Task> task = taskRepo.findById(taskId);
        return task.isPresent() &&
                task.get().getDevelopers().stream()
                        .anyMatch(dev -> dev.getName().equals(currentUsername));
    }
}

