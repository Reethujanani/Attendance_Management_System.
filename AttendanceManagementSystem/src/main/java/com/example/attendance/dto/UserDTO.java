package com.example.attendance.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data

public class UserDTO {

    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int mobileNo;
    private int isActive;
    private int isDeleted;
    private Timestamp createdAt;
    private Timestamp modifiedAt;


    private List<RoleDTO> roleList;


}
