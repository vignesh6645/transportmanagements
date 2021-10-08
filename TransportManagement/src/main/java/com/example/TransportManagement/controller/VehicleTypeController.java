package com.example.TransportManagement.controller;

import com.example.TransportManagement.baseresponse.BaseResponseRep;
import com.example.TransportManagement.dto.VehicleTypeDTO;
import com.example.TransportManagement.entity.VehicleType;
import com.example.TransportManagement.serviece.VehicleTypeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
@RequestMapping("/vehicleType")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeInterface vehicleTypeInterface;

    @RolesAllowed(value = "USER")
    @PostMapping("/create")
    //@Authorization(value = "Bearer")
    public BaseResponseRep addvehicle(@RequestBody VehicleTypeDTO vehicleTypeDTO){
        BaseResponseRep base;
        base= BaseResponseRep.builder().Data(vehicleTypeInterface.addvehicleType(vehicleTypeDTO)).build();
        return base;
    }
    @RolesAllowed(value = "USER")
    @GetMapping("/getAll")
    // @Authorization(value = "Bearer")
    public BaseResponseRep<List<VehicleType>>listAll(){
        BaseResponseRep<List<VehicleType>> base;
        base = BaseResponseRep.<List<VehicleType>> builder().Data(vehicleTypeInterface.listAll()).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
   // @Authorization(value = "Bearer")
    public BaseResponseRep updatedetail(@RequestBody VehicleTypeDTO vehicleTypeDTO){
        BaseResponseRep base ;
        base = BaseResponseRep.builder().Data(vehicleTypeInterface.updatevehicleType(vehicleTypeDTO)).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{id}")
   // @Authorization(value = "Bearer")
    public BaseResponseRep deletedetail(@PathVariable int id){
        BaseResponseRep base ;
        base = BaseResponseRep.builder().Data(vehicleTypeInterface.deletevehicleType(id)).build();
        return base;
    }



}
