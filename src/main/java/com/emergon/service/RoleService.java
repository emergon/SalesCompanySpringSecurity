/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergon.service;

import com.emergon.entities.Role;
import java.util.List;

/**
 *
 * @author anastasios
 */
public interface RoleService {
    
    List<Role> getAllRoles();

    public Role findById(Integer id);
}
