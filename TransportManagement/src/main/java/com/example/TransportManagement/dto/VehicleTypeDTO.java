package com.example.TransportManagement.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehicleTypeDTO {

    private Integer vehicle_type_id;

    private String vehicleTypeName;

    private int isActive;

    private int isDelete;

}
