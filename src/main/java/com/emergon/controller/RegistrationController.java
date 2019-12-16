package com.emergon.controller;

import com.emergon.entities.Role;
import com.emergon.entities.User;
import com.emergon.service.RoleService;
import com.emergon.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/register")
public class RegistrationController {
    
    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;
    
    @GetMapping("/showForm")
    public String showRegisterForm(Model m){
        m.addAttribute("user", new User());
        return "registration-form";
    }
    
    @PostMapping("/processRegistration")
    public String processRegistration(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model m
    ){
        if(result.hasErrors()){
            return "registration-form";
        }
        User existing = service.findByUsername(user.getUsername());
        if(existing!=null){
            m.addAttribute("user", new User());
            m.addAttribute("userExistsError", "Username already exists.");
            return "registration-form";
        }
        service.save(user);
        return "registration-confirmation";
    }
    
    @ModelAttribute("roles")
    public List<Role> getListOfRoles(){
        return roleService.getAllRoles();
    }
}
