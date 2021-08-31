package com.sshmygin.customerms.repository;

import com.sshmygin.customerms.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByActivationCode(String activationCode);
}
