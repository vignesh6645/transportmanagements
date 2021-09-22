package com.example.TransportManagement.repository;

import com.example.TransportManagement.entity.Load;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoadRepository extends JpaRepository<Load, Integer> {



}
