package com.sshmygin.mailsenderms.controller;

import com.sshmygin.mailsenderms.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailSenderController {

    private final MailSenderService mailSenderService;

    @GetMapping("/activate")
    public ResponseEntity<?> activateCustomerAccount(@RequestParam("code") String code) {
        mailSenderService.sendEmailActivationRequest(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
