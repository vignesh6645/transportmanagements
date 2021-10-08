package com.example.TransportManagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "role_name", nullable = false)
    private String roleName;


   /* @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(name = "userrole",
           joinColumns = {@JoinColumn(name = "user_id_fk")},
           inverseJoinColumns = {@JoinColumn(name = "role_id_fk")})
    private User user;*/


}