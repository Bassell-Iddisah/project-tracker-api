package com.gentleninja.repository;

import com.gentleninja.entity.Task;
import com.gentleninja.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
}
