package com.example.TransportManagement.servieceImplements;

import com.example.TransportManagement.dto.VehicleTypeDTO;
import com.example.TransportManagement.entity.VehicleType;
import com.example.TransportManagement.exception.ControllerExceptions;
import com.example.TransportManagement.repository.VehicleTypeRepository;
import com.example.TransportManagement.serviece.VehicleTypeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleTypeServieceImplements implements VehicleTypeInterface {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;


    @Override
    public VehicleType addvehicleType(VehicleTypeDTO vehicleTypeDTO) {


        VehicleType vehicleType = new VehicleType();


        vehicleType.setVehicleTypeName(vehicleTypeDTO.getVehicleTypeName());
        VehicleType obj = vehicleTypeRepository.save(vehicleType);
        return vehicleType;
    }

    @Override
    public Optional<VehicleType> updatevehicleType(VehicleTypeDTO vehicleTypeDTO) {

        Optional<VehicleType> existVehicleType = vehicleTypeRepository.findById(vehicleTypeDTO.getVehicle_type_id());
        if (existVehicleType.isPresent()){

            existVehicleType.get().setVehicle_type_id(vehicleTypeDTO.getVehicle_type_id());
            existVehicleType.get().setVehicleTypeName(vehicleTypeDTO.getVehicleTypeName());
        }
        else {
            throw new ControllerExceptions("404","No details found");
        }

        VehicleType obj = vehicleTypeRepository.save(existVehicleType.get());
        return existVehicleType;
    }

    @Override
    public VehicleType deletevehicleType(int id) {

        VehicleType vehicleType = new VehicleType();
        vehicleTypeRepository.deleteById(id);
        return vehicleType;
    }

    @Override
    public List<VehicleType> listAll() {
        List<VehicleType> obj=vehicleTypeRepository.findAll();
        return obj;
    }


}
