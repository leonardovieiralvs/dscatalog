package com.lsouzadev.aula.repository;

import com.lsouzadev.aula.entity.Role;
import com.lsouzadev.aula.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
