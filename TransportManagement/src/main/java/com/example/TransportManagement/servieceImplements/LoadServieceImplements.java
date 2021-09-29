package com.example.TransportManagement.servieceImplements;

import com.example.TransportManagement.dto.LoadDTO;
import com.example.TransportManagement.entity.Load;
import com.example.TransportManagement.entity.Vehicle;
import com.example.TransportManagement.exception.ControllerExceptions;
import com.example.TransportManagement.repository.LoadRepository;
import com.example.TransportManagement.repository.VehicleRespository;
import com.example.TransportManagement.serviece.LoadInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional

public class LoadServieceImplements implements LoadInterface{

    @Autowired
    private VehicleRespository vehicleRespository;

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public Load addload (LoadDTO loadDTO) {
        Load load = new Load();
        load.setLoadName(loadDTO.getLoadName());
        load.setDestination(loadDTO.getDestination());

        loadDTO.getVehicleId().forEach(vehicleDTO -> {
            Optional<Vehicle> vehicle = vehicleRespository.findById(vehicleDTO.getVehicle_id());

            if (vehicle.isPresent()){

                load.setVehicle(vehicle.get());

            }
            else {
                throw new ControllerExceptions("400","Type Valid info..");
            }
            Load obj = loadRepository.save(load);

        });

        return load;
    }
    @Override
    public Optional<Load> UpdateLoad(LoadDTO loadDTO) {

        Optional<Load> existLoad = loadRepository.findById(loadDTO.getLoad_id());
        if (existLoad.isPresent()){
            existLoad.get().setLoad_id(loadDTO.getLoad_id());
            existLoad.get().setLoadName(loadDTO.getLoadName());
            existLoad.get().setDestination(loadDTO.getDestination());
        }
        else {
            throw new ControllerExceptions("404","No Details Found");
        }
        loadDTO.getVehicleId().forEach(vehicleDTO -> {
            Optional<Vehicle> vehicle = vehicleRespository.findById(vehicleDTO.getVehicle_id());
            if (vehicle.isPresent()){

                existLoad.get().setVehicle(vehicle.get());
            }
            else {
                throw new ControllerExceptions("404","No Details Found");
            }
            Load obj1 = loadRepository.save(existLoad.get());

        });
        return existLoad ;
    }

    @Override
    public Load DeleteLoad(int id) {

        Load load= new Load();
        loadRepository.deleteById(id);
        return load;

    }

    @Override
    public List<Load> ListAll1() {
        List<Load> obj=loadRepository.findAll();
        return obj;
    }
}
