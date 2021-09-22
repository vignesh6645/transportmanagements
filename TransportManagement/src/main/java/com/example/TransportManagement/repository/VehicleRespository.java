package com.example.TransportManagement.repository;

import com.example.TransportManagement.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRespository extends JpaRepository<Vehicle,Integer> {

    Page<Vehicle> searchAllByvehicleNameLike(String s, Pageable paging);
}
