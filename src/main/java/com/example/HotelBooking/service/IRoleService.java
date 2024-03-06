package com.example.HotelBooking.service;

import com.example.HotelBooking.model.Role;
import com.example.HotelBooking.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
