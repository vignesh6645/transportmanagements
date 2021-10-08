package com.example.TransportManagement.controller;

import com.example.TransportManagement.baseresponse.APIResponse;
import com.example.TransportManagement.baseresponse.BaseResponseRep;
import com.example.TransportManagement.dto.UserDTO;
import com.example.TransportManagement.dto.UserRoleDto;
import com.example.TransportManagement.entity.User;
import com.example.TransportManagement.serviece.UserInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserInterface userInterface;


    @PostMapping(value="/create")
    public BaseResponseRep<User> SaveUser(@RequestBody UserDTO userDTO){
        BaseResponseRep<User> base;
        base = BaseResponseRep.<User>builder().Data(userInterface.adduser(userDTO)).build();
        return base;
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "user login ")
    public BaseResponseRep<UserRoleDto> tokenGenerate(@RequestBody UserRoleDto userRoleDTO) {
        BaseResponseRep<UserRoleDto> base;
        base = BaseResponseRep.<UserRoleDto>builder().Data(userInterface.generateToken(userRoleDTO)).build();
        return base;
    }

    @RolesAllowed(value="USER")
    @PutMapping("/update")
    public BaseResponseRep<Optional<User>> updateUser(@Valid @RequestBody UserDTO userDTO) {
        BaseResponseRep<Optional<User>> base;
        base=BaseResponseRep.<Optional<User>>builder().Data(userInterface.UpdateUser(userDTO)).build();
        return base ;
    }

    @RolesAllowed(value="USER")
    @GetMapping("/{id}")
    public BaseResponseRep<Optional<User>> FindById(@PathVariable int id)  {
        BaseResponseRep<Optional<User>> base;
        base =BaseResponseRep.<Optional<User>>builder().Data(userInterface.getuserById(id)).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/{offset}/{pageSize}/{name}")
  //  @ApiOperation(value = "userList", authorizations = {@Authorization(value = "Bearer")})
    public APIResponse<User> getPagination(@PathVariable int offset,@PathVariable int pageSize, @PathVariable String name){
        return userInterface.pageUser(offset, pageSize, name);
    }

    @RolesAllowed("USER")
    @DeleteMapping("/delete/{id}")
    public String delete (@PathVariable int id){
       // BaseResponseRep<User> baseResponseRep;
      //  baseResponseRep= BaseResponseRep.<User>builder().Data(userInterface.deletebyid(id)).build();
        userInterface.deletebyid(id);
        return "Success";
    }

}
