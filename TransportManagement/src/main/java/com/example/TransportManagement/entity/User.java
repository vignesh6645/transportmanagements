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
@SQLDelete(sql = "UPDATE User_vg SET is_delete = 1 WHERE user_id = ? ")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

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

    @OneToMany(cascade = {CascadeType.ALL}   )
    @JoinTable(name = "userrole",joinColumns = {@JoinColumn
            (name = "user_id_fk",referencedColumnName = "user_id")}
            ,inverseJoinColumns = {@JoinColumn(name = "role_id_fk",referencedColumnName = "id")}
    )
    private List<Role> listOfRole;

    public  User(User user)
    {
        this.id = user.getId();
        this.listOfRole = user.getListOfRole();
        this.name = user.getName();
    }
}
