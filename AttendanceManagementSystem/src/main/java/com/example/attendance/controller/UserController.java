package com.example.attendance.controller;

import com.example.attendance.dto.TokenDTO;
import com.example.attendance.dto.UserDTO;
import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService UserService;

    @PostMapping("/post")
    @ApiOperation(value = "User save")
    public UserDTO addUser( @RequestBody UserDTO user  ){
        return UserService.addUser(user);
    }

    //read method
    @GetMapping("/get")
    public List<UserDTO> getAllDetails(@RequestParam(value = "pageNo",defaultValue = "0") int pageNo,
                                       @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                       @RequestParam(value = "sortBy",defaultValue = "id") String sortBy){
        return UserService.listAllDetails(pageNo,pageSize,sortBy);
    }

    //read method by ID
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "User get by id", authorizations = {
            @Authorization(value = "Bearer") })
    public UserDTO getDetailsByID(@PathVariable int id){

        return UserService.getUserDetailsByID(id);
    }


    @PutMapping("/update")
    @ApiOperation(value = "User update", authorizations = {
            @Authorization(value = "Bearer") })
    public UserDTO updateStudent(@RequestBody UserDTO product) {
        return UserService.updateUserDetails(product);
    }


    //delete method+
    @DeleteMapping("/delete/{id}")@ApiOperation(value = "User delete by id", authorizations = {
            @Authorization(value = "Bearer") })
    public String deleteStudent(@PathVariable int id){
        return UserService.deleteDetailsById(id);
    }


    @PostMapping("/login")
    public String token1(@RequestBody TokenDTO tokenDTO)
    {
        return UserService.login(tokenDTO);
    }
}

