package com.example.TransportManagement.servieceImplements;

import com.example.TransportManagement.Utill.JwtUtil;
import com.example.TransportManagement.baseresponse.APIResponse;
import com.example.TransportManagement.dto.RoleDto;
import com.example.TransportManagement.dto.UserDTO;
import com.example.TransportManagement.dto.UserRoleDto;
import com.example.TransportManagement.entity.Role;
import com.example.TransportManagement.entity.User;
import com.example.TransportManagement.entity.UserRole;
import com.example.TransportManagement.exception.ControllerExceptions;
import com.example.TransportManagement.repository.RoleRepository;
import com.example.TransportManagement.repository.UserRepository;
import com.example.TransportManagement.repository.UserRoleRepository;
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
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public User adduser(UserDTO userDTO ) {
        User user = new User();
        user.setName(userDTO.getName());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setPassword(bcrypt.encode(userDTO.getPassword()));



        userRepository.save(user);
        addRole(userDTO.getRoles(), user);
        return user;

    }


   private void addRole(List<RoleDto> roles, User userDetail) {
        try {
            List<UserRole> userRoles = new LinkedList<>();
            if (Objects.nonNull(roles) && roles.size() > 0) {
                roles.stream().forEachOrdered(role -> {
                    Role role1 = roleRepository.findById(role.getId())
                            .orElseThrow(() -> new ControllerExceptions("404","Role is not found here"));
                    UserRole userRole = new UserRole();
                    userRole.setUser(userDetail);
                    userRole.setRole(role1);
                    userRoles.add(userRole);
                });
                userRoleRepository.saveAll(userRoles);
            }
        } catch (NoSuchElementException e) {
           throw new ControllerExceptions("901","Something went wrong!!! ");
        }
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
           Optional< User> user = userRepository.findByName(userRoleDTO.getName());
            boolean status = bcrypt.matches(userRoleDTO.getPassword(), user.get().getPassword());
            if (user.isPresent() && status == true) {
                List<UserRole> userRoles = userRoleRepository.findByUserId(user.get().getId());
                userRoles.stream().forEachOrdered(userRole -> {
                    Role role = userRole.getRole();
                    roles.add(role);
                });
                String Token = JwtUtil.generateToken("secret",
                        user.get().getId(), "user", user.get().getName(), roles);
                userRoleDTO.setName(user.get().getName());
                userRoleDTO.setId(user.get().getId());
                userRoleDTO.setRoleList(user.get().getRoles());
                userRoleDTO.setJwtToken(Token);
            }else throw  new ControllerExceptions("404","User details Not found ");
        } catch (NoSuchElementException e) {
            throw new ControllerExceptions("401","Unauthorized access!!! ");

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
            List<UserRole> userRoles = userRoleRepository.findByUserId(userDetail.get().getId());
            userRoles.stream().forEachOrdered(userRole -> {
                Role role = userRole.getRole();
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


