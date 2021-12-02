package com.example.attendance.repository;

import com.example.attendance.entity.Login_details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Login_detailsRepository extends JpaRepository<Login_details,Integer> {
    Optional<Login_details> findByUserIdAndTodayDate(Integer userId, Date date);

    List<Login_details> findByTodayDateAndIsLeave(String currentDate, int i);
}
