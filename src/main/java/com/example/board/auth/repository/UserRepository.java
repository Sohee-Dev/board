package com.example.board.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
}
