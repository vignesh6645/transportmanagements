package com.example.TransportManagement.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="userrole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_fk")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id_fk")
    private Role role;


}
