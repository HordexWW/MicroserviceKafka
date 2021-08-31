package com.sshmygin.customerms.controller;

import com.sshmygin.customerms.exception.TakenEmailException;
import com.sshmygin.customerms.model.Customer;
import com.sshmygin.customerms.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> allCustomers = customerService.getAllCustomers();
        return ResponseEntity
                .ok()
                .body(allCustomers);
    }

    @PostMapping
    public Customer sendRequestToRegisterNewCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return customer;
    }
}
