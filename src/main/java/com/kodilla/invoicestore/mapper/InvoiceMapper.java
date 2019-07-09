package com.kodilla.invoicestore.mapper;


import com.kodilla.invoicestore.domain.Invoice;
import com.kodilla.invoicestore.dto.InvoiceDto;
import com.kodilla.invoicestore.service.CompanyService;
import com.kodilla.invoicestore.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class InvoiceMapper {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    public InvoiceDto mapInvoiceToInvoiceDto(final Invoice invoice) {
        InvoiceDto invoiceDto = InvoiceDto.builder()
                .invoiceId(invoice.getInvoiceId())
                .invoiceType(invoice.getInvoiceType())
                .issuedDate(invoice.getIssuedDate())
                .description(invoice.getDescription())
                .amount(invoice.getAmount())
                .currency(invoice.getCurrency())
                .fileAddress(invoice.getFileAddress())
                .companyId(invoice.getCompany().getCompanyId())
                .userId(invoice.getUser().getUserId())
                .build();
        return invoiceDto;
    }

    public Invoice mapInvoiceDtoToInvoice(final InvoiceDto invoiceDto) {
        Invoice invoice = Invoice.builder()
                .invoiceId(invoiceDto.getInvoiceId())
                .invoiceType(invoiceDto.getInvoiceType())
                .issuedDate(invoiceDto.getIssuedDate())
                .description(invoiceDto.getDescription())
                .amount(invoiceDto.getAmount())
                .currency(invoiceDto.getCurrency())
                .fileAddress(invoiceDto.getFileAddress())
                .company(companyService.getCompany(invoiceDto.getCompanyId()).orElse(null))
                .user(userService.getUser(invoiceDto.getUserId()).orElse(null))
                .build();
        return invoice;
    }

    public List<InvoiceDto> mapInvoicesListToInvoiceDtosList(final List<Invoice> invoices) {
        return invoices.stream()
                .map(invoice -> mapInvoiceToInvoiceDto(invoice))
                .collect(Collectors.toList());
    }

    public List<Invoice> mapInvoiceDtosListToIncoicesList(final List<InvoiceDto> invoiceDtos) {
        return invoiceDtos.stream()
                .map(invoiceDto -> mapInvoiceDtoToInvoice(invoiceDto))
                .collect(Collectors.toList());
    }

}
