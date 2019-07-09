package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.EmailConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EmailConfigRepository extends CrudRepository<EmailConfig, Long> {
    @Override
    List<EmailConfig> findAll();

    @Override
    EmailConfig save(EmailConfig emailConfig);

    @Override
    Optional<EmailConfig> findById(Long emailConfigId);

    @Override
    void deleteById(Long emailConfigId);

}
