package com.example.attendance.service;

import com.example.attendance.dto.Login_detailsDTO;
import com.example.attendance.dto.TokenDTO;
import com.example.attendance.dto.UserDTO;
import com.example.attendance.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {



    UserDTO addUser(UserDTO user);

    List<UserDTO> listAllDetails(int pageNo , int pageSize, String sortBy);

    UserDTO getUserDetailsByID(int id);

    UserDTO updateUserDetails(UserDTO product);

    String deleteDetailsById(int id);

    String login(TokenDTO tokenDTO);

    UserDetails loadByUserName(String userName);
}
