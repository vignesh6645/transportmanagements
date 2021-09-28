package com.example.TransportManagement.controller;

import com.example.TransportManagement.baseresponse.BaseResponseRep;
import com.example.TransportManagement.dto.LoadDTO;
import com.example.TransportManagement.entity.Load;
import com.example.TransportManagement.serviece.LoadInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RequestMapping("/loads")
@RestController
public class LoadController {


    @Autowired
    private LoadInterface loadInterface;


    @RolesAllowed(value = "USER")
    @PostMapping("/create")
   // @Authorization(value = "Bearer")
    public BaseResponseRep<Object> adddetail(@RequestBody LoadDTO loadDTO){
       BaseResponseRep  base;
        base= BaseResponseRep.builder().Data(loadInterface.addload(loadDTO)).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/getAll")
    // @Authorization(value = "Bearer")
    public BaseResponseRep<List<Load>>listAll(){
        BaseResponseRep<List<Load>> base;
        base = BaseResponseRep.<List<Load>> builder().Data(loadInterface.ListAll1()).build();
        return base;
    }

    @RolesAllowed(value = "ADMIN")
    @PutMapping("/update")
   // @Authorization(value = "Bearer")
    public BaseResponseRep<Optional<Load>> updatedetail(@RequestBody LoadDTO loadDTO){
        BaseResponseRep<Optional<Load>> base ;
        base = BaseResponseRep.<Optional<Load>>builder().Data(loadInterface.UpdateLoad(loadDTO)).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{id}")
    //@Authorization(value = "Bearer")
    public BaseResponseRep<Load> deleteLoad(@PathVariable int id){
        BaseResponseRep <Load>base ;
        base = BaseResponseRep.<Load>builder().Data(loadInterface.DeleteLoad(id)).build();
        return base;
    }



}
