package com.kodilla.invoicestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.invoicestore.domain.Company;
import com.kodilla.invoicestore.domain.CurrencyEnum;
import com.kodilla.invoicestore.domain.InvoiceType;
import com.kodilla.invoicestore.domain.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDto {
    private Long invoiceId;
    private String invoiceNumber;
    private InvoiceType invoiceType;
    private LocalDate issuedDate;
    private String description;
    private Double amount;
    private CurrencyEnum currency;
    private String fileAddress;
    private Long companyId;
    private Long userId;
}
