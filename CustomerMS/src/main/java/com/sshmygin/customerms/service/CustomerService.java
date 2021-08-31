package com.sshmygin.customerms.service;

import com.sshmygin.customerms.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void addCustomer(Customer customer);
    void activateCustomerAccount(String activationCode);
    void sendRequestToRegisterNewCustomer(Customer customer);
}
