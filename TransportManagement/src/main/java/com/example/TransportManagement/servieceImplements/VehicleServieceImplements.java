package com.example.TransportManagement.servieceImplements;

import com.example.TransportManagement.baseresponse.APIResponse;
import com.example.TransportManagement.dto.VehicleDTO;
import com.example.TransportManagement.entity.User;
import com.example.TransportManagement.entity.Vehicle;
import com.example.TransportManagement.entity.VehicleType;
import com.example.TransportManagement.exception.ControllerExceptions;
import com.example.TransportManagement.repository.UserRepository;
import com.example.TransportManagement.repository.VehicleRespository;
import com.example.TransportManagement.repository.VehicleTypeRepository;
import com.example.TransportManagement.serviece.VehicleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class VehicleServieceImplements  implements VehicleInterface {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRespository vehicleRespository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Override
    public Vehicle addvehicle(VehicleDTO vehicleDTO) {


        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistrationNumber());

        Optional<VehicleType> vehicleType=vehicleTypeRepository
                .findById(vehicleDTO.getVehicle_type_id());
        vehicleType.ifPresent(vehicle::setVehicle_type_id);

        Vehicle finalVehicle = vehicle;
        vehicleDTO.getUserId().forEach(userDTO -> {
            Optional<User> user = userRepository.findById(userDTO.getId());
            user.ifPresent(finalVehicle::setUser);


        });

       Vehicle obj=vehicleRespository.save(vehicle);

        return vehicle;
    }

    @Override
    public Optional<Vehicle> updatevehicle(VehicleDTO vehicleDTO) {

        Optional<Vehicle> existVehicle = vehicleRespository.findById(vehicleDTO.getVehicle_id());
        if (existVehicle.isPresent()){

            existVehicle.get().setVehicleName(vehicleDTO.getVehicleName());
            existVehicle.get().setRegistrationNumber(vehicleDTO.getRegistrationNumber());
        }
        else {
            throw new ControllerExceptions("404","There is no vehicle here!! "  );
        }


        Optional<VehicleType> vehicleType=vehicleTypeRepository.findById(vehicleDTO
                .getVehicle_type_id());
        if (vehicleType.isPresent()){

            existVehicle.get().setVehicle_type_id(vehicleType.get());
        }
        else {
            throw new ControllerExceptions("404","Vehicle is not found");
        }
        vehicleDTO.getUserId().forEach(userDTO -> {
            Optional<User> user = userRepository.findById(userDTO.getId());
            if (user.isPresent()){

                existVehicle.get().setUser(user.get());
            }
            else {
                throw new ControllerExceptions("404","vehicle is not found");
            }
        });
       Vehicle obj= vehicleRespository.save(existVehicle.get());
        return existVehicle;
    }

  @Override
    public APIResponse<Vehicle> vehiclePagination(int offset, int pageSize, String vehicleName) {

        Pageable paging= PageRequest.of(offset,pageSize);
        Page<Vehicle> vehicles=vehicleRespository.searchAllByvehicleNameLike("%"+ vehicleName +"%", paging);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setResponse(vehicles);
        apiResponse.setRecordCount(vehicles.getTotalPages());
        return apiResponse;



    }

    @Override
    public Optional<Vehicle> findvehicleById(int id) {

        Optional<Vehicle> vehicle=vehicleRespository.findById(id);
        return vehicle;
    }

    @Override
    public Vehicle deletevehicle(int id) {


        Vehicle vehicle = new Vehicle();
        vehicleRespository.deleteById(id);
        return vehicle;
    }
}
