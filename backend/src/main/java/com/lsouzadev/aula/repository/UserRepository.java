package com.lsouzadev.aula.repository;

import com.lsouzadev.aula.entity.Category;
import com.lsouzadev.aula.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
