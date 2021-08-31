package com.sshmygin.mailsenderms.service;

import com.sshmygin.customerms.model.Customer;

public interface MailSenderService {
    void sendConfirmationEmail(Customer customer);
    void sendEmailActivationRequest(String activationCode);
}
