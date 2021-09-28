package com.example.TransportManagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "User_vg")
@SQLDelete(sql = "UPDATE User_vg SET deleted = 1 WHERE id = ? ")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;



   // @Column(name = "is_active",columnDefinition = "integer default 0")
   // private int isActive;

   // @Column(name = "is_delete",columnDefinition = "integer default 0")
    private int deleted;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime updateDateTime;

    @OneToMany(mappedBy = "role")
    private List<UserRole> roles;

    public User(User user)
    {
        this.id = user.getId();
        this.roles = user.getRoles();
        this.name = user.getName();
    }
}
