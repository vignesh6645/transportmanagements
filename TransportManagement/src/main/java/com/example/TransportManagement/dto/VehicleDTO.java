package com.example.TransportManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class VehicleDTO {

    private  Integer vehicle_id;

    private String vehicleName;

    private Integer registrationNumber;

    private Integer vehicle_type_id;

    private int isActive;

    private int isDelete;

    private List<UserDTO> userId;
}
