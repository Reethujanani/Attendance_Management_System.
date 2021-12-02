package com.example.attendance.service.ServiceImpl;

import com.example.attendance.dto.Login_detailsDTO;

import com.example.attendance.dto.TokenDTO;
import com.example.attendance.dto.UserDTO;
import com.example.attendance.entity.*;
import com.example.attendance.repository.Login_detailsRepository;
import com.example.attendance.repository.UserRoleRepository;
import com.example.attendance.repository.RoleRepository;
import com.example.attendance.repository.UserRepository;
import com.example.attendance.service.Login_detailsService;
import com.sun.istack.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service

public class Login_detailsServiceImpl implements Login_detailsService {
    private static final Logger LOGGER = null;

    @Autowired
    private Login_detailsRepository login_detailsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO addLogin_detailsService(Integer userId) {
    Login_details login_details = new Login_details();
    try {
        //getting current from the user
        Timestamp currentDate = new Timestamp(new java.util.Date().getTime());
        Optional<User> user = userRepository.findById(userId);
        login_details.setUser(user.get());
       Date date = new Date(new java.util.Date().getTime());
       login_details.setTodayDate(date.toString());
        Time time = new Time(new java.util.Date().getTime());
        /*Long currentTime = currentDate.getTime();*/

        if (time.getTime() >= 9.31) {
            login_details.setIsLate(1);
        } else {
            login_details.setIsLate(0);
        }
        login_details.setPunchIn(currentDate);
        login_details.setIsLeave(0);
        login_detailsRepository.save(login_details);
    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public UserDTO addPunchOutDetails(Integer userId) {
        try {
            Date date = new Date(new java.util.Date().getTime());
            Optional<Login_details> login_details1 = login_detailsRepository.findByUserIdAndTodayDate(userId, date);
            Timestamp currentDate = new Timestamp(new java.util.Date().getTime());
            login_details1.get().setPunchOut(currentDate);
            login_detailsRepository.save(login_details1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Login_detailsDTO> addLeaveDetails(String currentDate) throws ParseException {
        try {
            List<Login_details> loginDetails = login_detailsRepository.findByTodayDateAndIsLeave(currentDate, 1);
            ModelMapper modelMapper = new ModelMapper();
            List<Login_detailsDTO> login_detailsDTOS = new LinkedList<>();
            loginDetails.stream().forEachOrdered(login -> {
                Login_detailsDTO login_detailsDTO = modelMapper.map(login, Login_detailsDTO.class);
                login_detailsDTOS.add(login_detailsDTO);
            });
            return login_detailsDTOS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Login_detailsDTO> addLoginDetailsList(String currentDate) {
        List<Login_detailsDTO> login_detailsDTOS = new LinkedList<>();
        try {
            List<Login_details> loginDetails = login_detailsRepository.findByTodayDateAndIsLeave(currentDate, 0);
            ModelMapper modelMapper = new ModelMapper();
            loginDetails.stream().forEachOrdered(login -> {
                Login_detailsDTO login_detailsDTO = modelMapper.map(login, Login_detailsDTO.class);
                login_detailsDTOS.add(login_detailsDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login_detailsDTOS;
    }
}
