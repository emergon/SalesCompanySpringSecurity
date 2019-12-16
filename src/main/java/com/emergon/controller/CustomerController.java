package com.emergon.controller;

import com.emergon.entities.Customer;
import com.emergon.service.CustomerService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller//Used from Dispatcher Servlet to pass requests and get responses
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> list = customerService.findAllCustomers();
        model.addAttribute("listOfCustomers", list);
        return "customer/listCustomer";
    }

    @GetMapping("/create")
    public String getCustomerForm(Model model) {
        Customer c = new Customer();
        model.addAttribute("customer", c);
        return "customer/formCustomer";
    }

    @PostMapping("/create")
    public String addCustomer(
            @ModelAttribute("customer") @Valid Customer c,BindingResult result
            , Model model) {
        if(result.hasErrors()){
            return "customer/formCustomer";
        }
        customerService.saveCustomer(c);
        return "redirect:/customer/list";
    }

    @GetMapping("/update")
    public String showFormUpdate(@RequestParam("customerId") int id, Model model) {
        Customer c = customerService.findById(id);
        model.addAttribute("customer", c);
        return "customer/formCustomer";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id, Model model) {
        customerService.removeCustomer(id);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchName") String searchName,
            Model model) {
        List<Customer> customers = customerService.searchCustomers(searchName);
        model.addAttribute("listOfCustomers", customers);
        return "customer/listCustomer";
    }

}
