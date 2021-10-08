package com.example.TransportManagement.servieceImplements;

import com.example.TransportManagement.Utill.JwtUtil;
import com.example.TransportManagement.baseresponse.APIResponse;
import com.example.TransportManagement.dto.UserDTO;
import com.example.TransportManagement.dto.UserRoleDto;
import com.example.TransportManagement.entity.Role;
import com.example.TransportManagement.entity.User;
import com.example.TransportManagement.exception.ControllerExceptions;
import com.example.TransportManagement.repository.RoleRepository;
import com.example.TransportManagement.repository.UserRepository;
import com.example.TransportManagement.repository.VehicleRespository;
import com.example.TransportManagement.serviece.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServieceImplements implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRespository vehicleRespository;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public User adduser(UserDTO userDTO ) {
        User user = new User();
        user.setName(userDTO.getName());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(userDTO.getPassword()));
        List<Role> roleList=new LinkedList<>();
        userDTO.getRoles().forEach(role -> {
            Role role1= new Role();
            role1.setRoleName(role.getRoleName());
            roleList.add(role1);
        });
        user.setListOfRole(roleList);
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getuserById(int id) {

       Optional<User>user=userRepository.findById(id);
        return user;
    }

    @Override
    public Optional<User> UpdateUser(UserDTO userDTO) {

        Optional<User> existUser = userRepository.findById(userDTO.getId());
        if(existUser.isPresent()){
            existUser.get().setName(userDTO.getName());
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            existUser.get().setPassword(bcrypt.encode(userDTO.getPassword()));

            List<Role> roleList= new LinkedList<>();
            userDTO.getRoles().forEach(role -> {
                Role role1 = roleRepository.findById(role.getId()).orElse(null);
                role1.setRoleName(role.getRoleName());
                roleList.add(role1);

            });
            existUser.get().setListOfRole(roleList);
        }
        else {
            throw new ControllerExceptions("901","Something went wrong!!! ");
        }

        userRepository.save(existUser.get());
        return existUser;

    }

    @Override
    public UserRoleDto generateToken(UserRoleDto userRoleDTO) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        List<Role> roles = new LinkedList<>();

        try {
            Optional<User> user = userRepository.findByName(userRoleDTO.getName());
            boolean status = bcrypt.matches(userRoleDTO.getPassword(), user.get().getPassword());
            if (user.isPresent() && status == true) {

                user.get().getListOfRole().forEach(role -> {
                    Role role1 = new Role();
                    role1.setRoleName(role.getRoleName());
                    roles.add(role);
                });

                String Token = JwtUtil
                        .generateToken("secret",
                                user.get().getId(),"user",user.get().getName(),roles);
                userRoleDTO.setName(user.get().getName());
                userRoleDTO.setId(user.get().getId());
                userRoleDTO.setJwtToken(Token);

            }

        }catch (NoSuchElementException e){
            throw new  ControllerExceptions("401","Unauthorized access!!! ");
        }
        return userRoleDTO;
    }
    @Override
    public APIResponse<User> pageUser(int offset, int pageSize, String name) {
        APIResponse apiResponse = new APIResponse();
        try {
            Pageable paging = PageRequest.of(offset, pageSize);
            Page<User> users = userRepository.searchAllByNameLike("%" + name + "%", paging);
            apiResponse.setResponse(users);
            apiResponse.setRecordCount(users.getTotalPages());
        } catch (NoSuchElementException e) {
            throw new ControllerExceptions("404", "No details found");
        }
        return apiResponse;
    }

    @Override
    public User deletebyid(int id) {
        User user = new User();
        userRepository.deleteById(id);
        return user;
    }

    public UserDetails loadByUserName(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByName(username);
        List<Role> roles = new LinkedList<>();
        if (userDetail == null) {
            throw new ControllerExceptions("404","User details Not Found..");
        }
        else{

            userDetail.get().getListOfRole().forEach(role -> {
                Role role1 = new Role();
                role1.setRoleName(role.getRoleName());
                roles.add(role);
            });

            return new org.springframework.security.core.userdetails
                    .User(userDetail.get().getName(), userDetail.get().getPassword(), getAuthority(roles));
        }
    }

    private List getAuthority(List<Role> role){
        List authorities=new ArrayList();
        role.stream().forEachOrdered(role1 -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" +role1.getRoleName()));
        });
        return authorities;
    }

}