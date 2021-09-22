package com.example.TransportManagement.dto;

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

    private List<RoleDto> roles;

}
