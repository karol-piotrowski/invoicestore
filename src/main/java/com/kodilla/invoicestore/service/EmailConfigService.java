package com.kodilla.invoicestore.service;


import com.kodilla.invoicestore.domain.EmailConfig;
import com.kodilla.invoicestore.repository.EmailConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailConfigService {
    @Autowired
    EmailConfigRepository emailConfigRepository;

    public List<EmailConfig> getEmailConfigs() {
        return emailConfigRepository.findAll();
    }

    public Optional<EmailConfig> getEmailConfig(Long id) {
        return emailConfigRepository.findById(id);
    }

    public EmailConfig saveEmailConfig(EmailConfig emailConfig) {
        return emailConfigRepository.save(emailConfig);
    }

    public void deleteEmailConfig(Long id) {
        emailConfigRepository.deleteById(id);
    }

    public long count() {
        return emailConfigRepository.count();
    }
}
