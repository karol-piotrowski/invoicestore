package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.Invoice;
import com.kodilla.invoicestore.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    @Override
    List<Invoice> findAll();

    @Override
    Invoice save(Invoice invoice);

    @Override
    Optional<Invoice> findById(Long invoiceId);

    @Override
    void deleteById(Long invoiceId);

    List<Invoice> findInvoicesByUser(User user);

    List<Invoice> findInvoicesByUserAndIssuedDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
