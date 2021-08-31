package com.sshmygin.customerms.service;

import com.sshmygin.customerms.exception.AccountActivationException;
import com.sshmygin.customerms.exception.TakenEmailException;
import com.sshmygin.customerms.kafka.producer.KafkaCustomerProduser;
import com.sshmygin.customerms.model.Customer;
import com.sshmygin.customerms.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final KafkaCustomerProduser kafkaCustomerProduser;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addCustomer(Customer customer) throws TakenEmailException {

        String customerEmail = customer.getEmail();

        Customer existingCustomer = customerRepository.findByEmail(customerEmail).orElse(null);

        if (existingCustomer == null) {
            customer.setActivationCode(UUID.randomUUID().toString());
            customerRepository.save(customer);
            log.info("CustomerMS Account registration -> Customer account has been registered. ->" +
                    " Email: {}", customerEmail);
            sendRequestToRegisterNewCustomer(customer);
        } else {
            log.info("CustomerMS Account Activation -> Customer account hasn't been registered. ->" +
                    " Customer with email {} is already exists.", customerEmail);
            throw new TakenEmailException("Customer with email " + customerEmail + " is already exists.");
        }

    }

    @Override
    public void activateCustomerAccount(String email) {

        Customer existingCustomer = customerRepository.findByEmail(email).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setActivated(true);
            customerRepository.save(existingCustomer);
            log.info("CustomerMS Account Activation -> Account has been activated -> {}", email);
        } else {
            log.info("CustomerMS Account Activation -> Account was not confirmed. Account email: {}", email);
            throw new AccountActivationException("Account has already activated. Account email: {}" + email);
        }
    }

    @Override
    public void sendRequestToRegisterNewCustomer(Customer customer) {
        kafkaCustomerProduser.send(customer);
        log.info("CustomerMS Sending Request To Confirm Email Address -> Request has been sent ->" +
                " Email address: {}", customer.getEmail());
    }
}
