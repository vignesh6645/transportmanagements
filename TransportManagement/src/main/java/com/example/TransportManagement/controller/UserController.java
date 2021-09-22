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
    public BaseResponseRep<User> Saveuser(@RequestBody UserDTO userDTO){
        BaseResponseRep<User> base=null;
        base = BaseResponseRep.<User>builder().Data(userInterface.adduser(userDTO)).build();
        return base;
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "user login ")
    public BaseResponseRep<UserRoleDto> tokengenrate(@RequestBody UserRoleDto userRoleDTO) {
        BaseResponseRep<UserRoleDto> base=null;
        base = BaseResponseRep.<UserRoleDto>builder().Data(userInterface.logOfUser(userRoleDTO)).build();
        return base;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/{offset}/{pageSize}/{name}")
    private APIResponse<User> getUserWithPagination
            (@PathVariable int offset, @PathVariable int pageSize, @PathVariable String name) {
        return (APIResponse<User>) userInterface.userpagination(offset, pageSize, name);
    }

    @RolesAllowed(value="ADMIN")
    @PutMapping("/update")
    public BaseResponseRep<Optional<User>> updateuser(@Valid @RequestBody UserDTO userDTO) {
        BaseResponseRep<Optional<User>> base=null;
        base=BaseResponseRep.<Optional<User>>builder().Data(userInterface.UpdateUser(userDTO)).build();
        return base ;
    }

    @RolesAllowed(value="USER")
    @GetMapping("/{id}")
    public BaseResponseRep<Optional<User>> FindById(@PathVariable int id)  {
        BaseResponseRep<Optional<User>> base=null;
        base =BaseResponseRep.<Optional<User>>builder().Data(userInterface.getuserById(id)).build();
        return base;
    }

    @RolesAllowed(value="ADMIN")
    @PutMapping("/delete")
    public BaseResponseRep<Optional<User>> deletesoft(@RequestBody UserDTO userDTO) {
        BaseResponseRep<Optional<User>> base=null;
        base=BaseResponseRep.<Optional<User>>builder().Data(userInterface.deleteuser(userDTO)).build();
        return base ;
    }


}