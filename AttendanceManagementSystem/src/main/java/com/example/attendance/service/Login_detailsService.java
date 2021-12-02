package com.example.attendance.service;

import com.example.attendance.dto.Login_detailsDTO;
import com.example.attendance.dto.TokenDTO;
import com.example.attendance.dto.UserDTO;
import com.example.attendance.entity.Login_details;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface Login_detailsService{

    UserDTO addLogin_detailsService(Integer userId);

    UserDTO addPunchOutDetails(Integer userId);

    List<Login_detailsDTO> addLeaveDetails(String currentDate) throws ParseException;

    List<Login_detailsDTO> addLoginDetailsList(String currentDate);
}
    