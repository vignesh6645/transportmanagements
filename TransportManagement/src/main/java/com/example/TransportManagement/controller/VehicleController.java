package com.example.TransportManagement.controller;

import com.example.TransportManagement.baseresponse.APIResponse;
import com.example.TransportManagement.baseresponse.BaseResponseRep;
import com.example.TransportManagement.dto.VehicleDTO;
import com.example.TransportManagement.entity.Vehicle;
import com.example.TransportManagement.serviece.VehicleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RequestMapping("/vehicle")
@RestController
public class VehicleController {

   @Autowired
    private VehicleInterface vehicleInterface;

  @RolesAllowed(value = "USER")
  @PostMapping("/create")
 // @Authorization(value = "Bearer")
  public BaseResponseRep <Optional<Vehicle>>addvehicle(@RequestBody VehicleDTO vehicleDTO){
      BaseResponseRep base;
      base= BaseResponseRep.builder().Data(vehicleInterface.addvehicle(vehicleDTO)).build();
      return base;
  }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
   // @Authorization(value = "Bearer")
    public BaseResponseRep<Optional<Vehicle>> updatedetail(@RequestBody VehicleDTO vehicleDTO){
        BaseResponseRep<Optional<Vehicle>> base ;
        base = BaseResponseRep.<Optional<Vehicle>>builder().Data(vehicleInterface.updatevehicle(vehicleDTO)).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/page/{offset}/{pageSize}/{vehicleName}")
    //@Authorization(value = "Bearer")
    private APIResponse<Vehicle> vehiclePagination
            (@PathVariable int offset, @PathVariable int pageSize, @PathVariable String vehicleName){
        return vehicleInterface.vehiclePagination(offset, pageSize, vehicleName);
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/vehicleId/{id}")
    //@Authorization(value = "Bearer")
    public BaseResponseRep<Optional<Vehicle>>findByVehicleId(@PathVariable int id){
      BaseResponseRep<Optional<Vehicle>> base;
      base = BaseResponseRep.<Optional<Vehicle>>builder().Data(vehicleInterface.findvehicleById(id)).build();
      return base;
    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{id}")
   // @Authorization(value = "Bearer")
    public BaseResponseRep deleteLoad(@PathVariable int id){
        BaseResponseRep base ;
        base = BaseResponseRep.builder().Data(vehicleInterface.deletevehicle(id)).build();
        return base;
    }

}
