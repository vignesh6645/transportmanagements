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
@SQLDelete(sql = "UPDATE vehicle SET is_delete = 1 WHERE  vehicle_id  = ? ")
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer id;

    @Column(name = "vehicle_name",nullable = false)
    private String vehicleName;

    @Column(name = "registration_number")
    private int registrationNumber;

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

    @OneToOne
    @JoinColumn(name = "fk_vehicle_type_id")
    private VehicleType vehicle_type_id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;


}
