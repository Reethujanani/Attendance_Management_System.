package com.example.attendance.controller;

import com.example.attendance.dto.Login_detailsDTO;
import com.example.attendance.dto.TokenDTO;
import com.example.attendance.dto.UserDTO;
import com.example.attendance.entity.Login_details;
import com.example.attendance.entity.User;
import com.example.attendance.service.Login_detailsService;
import com.example.attendance.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")

public class Login_detailsController {

    @Autowired
    private Login_detailsService login_detailsService;

    @GetMapping("/punchin")
    @ApiOperation(value = "User save", authorizations = {
            @Authorization(value = "Bearer") })
    public UserDTO addLogin_details(@RequestParam Integer userId ){
        return login_detailsService.addLogin_detailsService(userId);
    }

    @GetMapping("/punchout")
    @ApiOperation(value = "User save", authorizations = {
            @Authorization(value = "Bearer") })
    public UserDTO addPunchOutDetails(@RequestParam Integer userId ){
        return login_detailsService.addPunchOutDetails(userId);
    }


    @GetMapping("/leavedetails")
    @ApiOperation(value = "User save", authorizations = {
            @Authorization(value = "Bearer") })
    public List<Login_detailsDTO> addLeaveDetails(@RequestParam String currentDate ) throws ParseException {
        return login_detailsService.addLeaveDetails(currentDate);
    }

    @GetMapping("/logindetails")
    @ApiOperation(value = "User save", authorizations = {
            @Authorization(value = "Bearer") })
    public List<Login_detailsDTO> addLoginDetailsList(@RequestParam String currentDate ){
        return login_detailsService.addLoginDetailsList(currentDate);
    }
}


