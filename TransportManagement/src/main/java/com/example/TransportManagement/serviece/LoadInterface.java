package com.example.TransportManagement.serviece;

import com.example.TransportManagement.dto.LoadDTO;
import com.example.TransportManagement.entity.Load;

import java.util.List;
import java.util.Optional;

public interface LoadInterface {
    Load addload(LoadDTO loadDTO);

    Optional<Load> UpdateLoad(LoadDTO loadDTO);

    Load DeleteLoad(int id);

    List<Load> ListAll1();
}
