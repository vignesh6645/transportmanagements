package com.example.TransportManagement.serviece;

import com.example.TransportManagement.baseresponse.APIResponse;
import com.example.TransportManagement.dto.UserDTO;
import com.example.TransportManagement.dto.UserRoleDto;
import com.example.TransportManagement.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserInterface {

    User adduser(UserDTO userDTO);



    Optional<User> getuserById(int id);

    

    Optional<User> UpdateUser(UserDTO userDTO);

   UserRoleDto generateToken(UserRoleDto userRoleDTO);


    APIResponse<User> pageUser(int offset, int pageSize, String name);

    User deletebyid(int id);


    // User deleteUser(int id);
}
