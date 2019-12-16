/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergon.service;

import com.emergon.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author anastasios
 */
public interface UserService extends UserDetailsService
{
    User findByUsername(String username);
    void save(User user);
}
