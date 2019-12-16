package com.emergon.dao;

import com.emergon.entities.Role;
import java.util.List;

public interface RoleDao {

    public Role findRoleByName(String role_user);
    List<Role> findAll();

    public Role findById(Integer id);
}
