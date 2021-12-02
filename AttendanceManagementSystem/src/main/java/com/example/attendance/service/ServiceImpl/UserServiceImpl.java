package com.example.attendance.service.ServiceImpl;

import ch.qos.logback.classic.Logger;
import com.example.attendance.dto.TokenDTO;
import com.example.attendance.dto.UserDTO;
import com.example.attendance.entity.Role;
import com.example.attendance.entity.User;
import com.example.attendance.entity.UserRole;
import com.example.attendance.exception.UnAuthException;
import com.example.attendance.repository.RoleRepository;
import com.example.attendance.repository.UserRepository;
import com.example.attendance.AttendanceMgmtApplication;
import com.example.attendance.repository.UserRoleRepository;
import com.example.attendance.service.UserService;
import com.example.attendance.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    JwtUtil jwtUtil = new JwtUtil();

    private static final Logger LOGGER= (Logger) LoggerFactory.getLogger(AttendanceMgmtApplication.class);

    @Override
    public UserDTO addUser(UserDTO user) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            User user1 = modelMapper.map(user, User.class);
            List<UserRole> roleList = new LinkedList<>();
            user.getRoleList().stream().forEachOrdered(action -> {
                UserRole userRole = new UserRole();
                Optional<Role> role = roleRepository.findById(action.getId());
                userRole.setRole(role.get());
                userRole.setUser(user1);
                roleList.add(userRole);
            });
            Optional<User> userCheck = userRepository.findByUserName(user.getUserName());
            if (userCheck.isPresent()) {
                LOGGER.info("Username Already available");
                throw new UnAuthException("Already available");
            }
            user1.setUserRoles(roleList);
            try {
                userRepository.save(user1);
                LOGGER.info("user saved successfully");

            } catch (Exception e) {
                LOGGER.info("Error while saving user");
            }
            return user;
        } catch (UnAuthException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDTO> listAllDetails(int pageNo , int pageSize, String sortBy) {
        List<UserDTO> userDTOS = new LinkedList<>();
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
            Page<User> users = userRepository.findAll(pageable);
            users.stream().forEachOrdered(action->{
                ModelMapper modelMapper = new ModelMapper();
                UserDTO userDTO = modelMapper.map(action,UserDTO.class);
                userDTOS.add(userDTO);
            });
            LOGGER.info("user list");
        }
        catch(Exception e){
            e.getMessage();
        }
        return userDTOS;
    }

    @Override
    public UserDTO getUserDetailsByID(int id) {
        Optional<User> user = userRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user.get(),UserDTO.class);
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO updateUserDetails(UserDTO user) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            User user1 = modelMapper.map(user, User.class);
            List<UserRole> roleList = new LinkedList<>();
            List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
            userRoleRepository.deleteAll(userRoles);
            user.getRoleList().stream().forEachOrdered(action -> {
                UserRole userRole = new UserRole();
                Optional<Role> role = roleRepository.findById(action.getId());
                userRole.setRole(role.get());
                userRole.setUser(user1);
                roleList.add(userRole);
            });
            user1.setUserRoles(roleList);
            try {
                userRepository.save(user1);
                LOGGER.info("user update successfully");

            } catch (Exception e) {
                LOGGER.info("Error while saving user");
            }
            return user;
        } catch (UnAuthException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public String deleteDetailsById(int id) {
        try {
            userRepository.deleteById(id);
            LOGGER.info("user deleted successfully");
            return "successfully deleted";
        }
        catch (Exception e){
            e.getMessage();
        }
            return null;
    }

    @Override
    public String login(TokenDTO tokenDTO) {
        Optional<User> Obj = userRepository.findByUserNameAndPassword(tokenDTO.getUsername(),tokenDTO.getPassword());
        String token= "";
        try
        {
            if(Obj.isPresent())
            {
                List<Role> roles = new LinkedList<>();
                Obj.get().getUserRoles().stream().forEachOrdered(role->{
                    Role newRole = new Role();
                    newRole.setRoleName(role.getRole().getRoleName());
                    newRole.setId(role.getId());
                    roles.add(newRole);
                });
              token = JwtUtil.generateToken("Jwt_Token",Obj.get().getId(),roles,Obj.get().getUserName());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public UserDetails loadByUserName(String userName) {
        try {
            Optional<User> user = userRepository.findByUserName(userName);
            Set authority = authority(userName);

            return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), authority);
        }
        catch(Exception e){
            e.getMessage();
        }
        return null;
    }
    public Set authority(String userName){
        try {
            Optional<User> user = userRepository.findByUserName(userName);
            Set authority = new HashSet();
            user.get().getUserRoles().stream().forEachOrdered(action -> {
                authority.add(new SimpleGrantedAuthority("ROLE_" + action.getRole().getRoleName()));
            });

            return authority;
        }
        catch (Exception e){
            e.getMessage();
        }
            return null;
    }

}




