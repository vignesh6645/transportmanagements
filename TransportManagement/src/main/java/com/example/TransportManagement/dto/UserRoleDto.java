package com.example.TransportManagement.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRoleDto {

    private Integer id;

    private String name;

    private String jwtToken;

    private String password;

   // private List<UserRole> roleList;


}