package com.example.TransportManagement.entity;


import lombok.Getter;

import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;


@Getter
@Setter
@SQLDelete(sql = "UPDATE vehicleType SET is_delete = 1 WHERE vehicle_type_id = ? ")
@Entity
@Table(name = "vehicleType")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id")
    private Integer vehicle_type_id;

    @Column(name = "vehicleName")
    private String vehicleTypeName;

    @Column(name = "is_active",columnDefinition = "integer default 0")
    private int isActive;

    @Column(name = "is_delete",columnDefinition = "integer default 0")
    private int isDelete;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime updateDateTime;
}
