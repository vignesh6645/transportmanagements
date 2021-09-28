package com.example.TransportManagement.repository;

import com.example.TransportManagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    




    Optional<User> findByName(String name);


    Page<User> searchAllByNameLike(String s, Pageable paging);


}
