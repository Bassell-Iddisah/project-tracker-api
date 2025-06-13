package com.gentleninja.repository;

import com.gentleninja.entity.Role;
import com.gentleninja.entity.RoleType;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
    List<Role> findAll();
}
