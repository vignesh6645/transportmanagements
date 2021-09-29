package com.example.TransportManagement.entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.time.LocalDateTime;


@Entity

@Getter
@Setter
@SQLDelete(sql = "UPDATE loads SET is_delete = 1 WHERE load_id = ? ")
@Table(name = "loads")
public class Load {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "load_id")
    private Integer load_id;

    @Column(name = "load_name")
    private String loadName;

    @Column(name = "destination")
    private String destination;

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

    @ManyToOne
    @JoinColumn(name = "fk_vehicle_id")
    private Vehicle vehicle;
}
