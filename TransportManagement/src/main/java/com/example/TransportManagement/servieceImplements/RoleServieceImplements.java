package com.example.TransportManagement.servieceImplements;

import com.example.TransportManagement.dto.UserRoleDto;
import com.example.TransportManagement.entity.Role;
import com.example.TransportManagement.entity.User;
import com.example.TransportManagement.entity.UserRole;
import com.example.TransportManagement.repository.RoleRepository;
import com.example.TransportManagement.repository.UserRepository;
import com.example.TransportManagement.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class RoleServieceImplements  {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

   /* private void addRole (List<RoleDto> roles, User userDetail) {
        try {
            List<UserRole> userRoles = new LinkedList<>();
            if (Objects.nonNull(roles) && roles.size() > 0) {
                roles.stream().forEachOrdered(role -> {
                    Role role1 = roleRepository.findById(role.getId())
                            .orElseThrow(() -> new RuntimeException("role is not here"));
                    UserRole userRole = new UserRole();
                    userRole.setUser(userDetail);
                    userRole.setRole(role1);
                    userRoles.add(userRole);
                });
                userRoleRepository.saveAll(userRoles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public UserRole addRole(UserRoleDto userRoleDto){
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDto.getId());

        Optional<User> user = userRepository.findById(userRoleDto.getId());
        user.ifPresent(userRole::setUser);

        UserRole finalUserRole = userRole;
        userRoleDto.getRoleList().forEach(userRole1 -> {
            Optional<Role> role= roleRepository.findById(userRole1.getId());
            user.ifPresent(finalUserRole::setUser);
        });
        userRoleRepository.save(userRole);
        return userRole;
    }

}

