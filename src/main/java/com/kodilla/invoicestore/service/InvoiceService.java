package com.kodilla.invoicestore.service;

import com.kodilla.invoicestore.domain.Invoice;
import com.kodilla.invoicestore.domain.User;
import com.kodilla.invoicestore.repository.InvoiceRepository;
import com.kodilla.invoicestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public long count() {
        return invoiceRepository.count();
    }

    public List<Invoice> getInvoicesForUser(User user) {
        return invoiceRepository.findInvoicesByUser(user);
    }

    public List<Invoice> getInvoicesByUserAndIssuedDateBetween(User user, LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findInvoicesByUserAndIssuedDateBetween(user, startDate, endDate);
    }
    public List<Invoice> getInvoicesForUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(new User());
        return invoiceRepository.findInvoicesByUser(user);
    }

    public List<Invoice> getInvoicesByUserIdAndIssuedDateBetween(Long userId, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId).orElse(new User());
        return invoiceRepository.findInvoicesByUserAndIssuedDateBetween(user, startDate, endDate);
    }

}
