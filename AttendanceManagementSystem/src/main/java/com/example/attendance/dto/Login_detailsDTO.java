package com.example.attendance.dto;

import com.example.attendance.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class Login_detailsDTO {

   private Integer id;

   private Timestamp punchIn;

   private Timestamp punchOut;

   private Integer isLate;

   private Integer isLeave;

   private int isActive;

   private int isDeleted;

   private Date todayDate;

   private Timestamp createdAt;

   private Timestamp modifiedAt;

   private UserDTO user;
}
