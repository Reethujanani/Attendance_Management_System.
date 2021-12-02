package com.example.attendance.repository;

import com.example.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository <User, Integer>{


  Optional<User> findByUserName(String username);
  Optional<User> findByUserNameAndPassword(String username, String password);
}
