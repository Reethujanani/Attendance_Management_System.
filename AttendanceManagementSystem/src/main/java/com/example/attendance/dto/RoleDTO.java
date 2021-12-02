package com.example.attendance.dto;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class RoleDTO {

    private int id;
    private String roleName;
    private String roleDescription;
    private int isActive;
    private int isDeleted;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

}
