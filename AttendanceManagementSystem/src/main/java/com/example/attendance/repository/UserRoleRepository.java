package com.example.attendance.repository;


import com.example.attendance.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    List<UserRole> findByUserId(int id);
}
