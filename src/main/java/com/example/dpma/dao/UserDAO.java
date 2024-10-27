package com.example.dpma.dao;
import com.example.dpma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDAO extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findUserByUsername(String username);

}