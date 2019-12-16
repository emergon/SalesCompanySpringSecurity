/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergon.service;

import com.emergon.dao.RoleDao;
import com.emergon.dao.UserDao;
import com.emergon.entities.Role;
import com.emergon.entities.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao udao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return udao.findByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!userHasRoleUser(user)) {
            // give user default role of "employee"
            user.addRole(roleDao.findRoleByName("ROLE_USER"));
        }
        //user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
        udao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = udao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRname()));
        }
        return authorities;

//return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRname())).collect(Collectors.toList());
    }

    //find if user has role USER
    private boolean userHasRoleUser(User u) {
        for (Role r : u.getRoles()) {
            if (r.getRname().equals("ROLE_USER")) {
                return true;
            }
        }
        return false;
    }

}
