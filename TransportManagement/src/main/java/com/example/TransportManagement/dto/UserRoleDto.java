package com.example.TransportManagement.dto;
import com.example.TransportManagement.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserRoleDto {

    private Integer id;

    private String name;

    private String jwtToken;

    private String password;

    private List<UserRole> roleList;


}