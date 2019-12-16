package com.emergon.service;

import com.emergon.entities.Customer;
import java.util.List;

public interface CustomerService {
    
    Customer findById(int id);

    void saveCustomer(Customer customer);

    void removeCustomer(int id);

    //Customer updateCustomer(Customer customer);

    List<Customer> findAllCustomers();

    public List<Customer> searchCustomers(String searchName);
}
