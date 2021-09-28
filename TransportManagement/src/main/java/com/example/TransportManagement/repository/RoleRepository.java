package com.example.TransportManagement.repository;

import com.example.TransportManagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {



}
