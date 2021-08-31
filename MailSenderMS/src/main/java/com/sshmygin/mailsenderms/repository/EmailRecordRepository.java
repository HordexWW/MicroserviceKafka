package com.sshmygin.mailsenderms.repository;

import com.sshmygin.mailsenderms.model.EmailRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRecordRepository extends JpaRepository<EmailRecord, Long> {
    Optional<EmailRecord> findByEmail(String email);
    Optional<EmailRecord> findByActivationCode(String activationCode);
}
