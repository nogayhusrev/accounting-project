package com.accounting.repository;

import com.accounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findUserByUsername(String username);


}
