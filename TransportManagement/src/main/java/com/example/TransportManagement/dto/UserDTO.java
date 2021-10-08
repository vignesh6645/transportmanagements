package com.example.TransportManagement.dto;

import com.example.TransportManagement.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    private String name;

    private String password;

    private int isActive;

    private int isDelete;

    private String roleName;

    private List<Role> roles;

}
