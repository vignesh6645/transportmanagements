package com.example.TransportManagement.repository;

import com.example.TransportManagement.dto.VehicleTypeDTO;
import com.example.TransportManagement.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {


}
