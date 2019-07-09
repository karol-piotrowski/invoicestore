package com.kodilla.invoicestore.mapper;

import com.kodilla.invoicestore.domain.Company;
import com.kodilla.invoicestore.dto.CompanyDto;
import com.kodilla.invoicestore.service.CompanyService;
import com.kodilla.invoicestore.service.InvoiceService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CompanyMapper {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private InvoiceService invoiceService;

    public CompanyDto mapCompanyToCompanyDto(final Company company) {
        CompanyDto companyDto = CompanyDto.builder()
                .companyId(company.getCompanyId())
                .taxId(company.getTaxId())
                .companyName(company.getCompanyName())
                .invoiceIdList(company.getInvoices().stream().map(invoice -> invoice.getInvoiceId()).collect(Collectors.toList()))
                .build();
        return companyDto;
    }

    public Company mapCompanyDtoToCompany(final CompanyDto companyDto) {
        Company company = Company.builder()
                .companyId(companyDto.getCompanyId())
                .taxId(companyDto.getTaxId())
                .companyName(companyDto.getCompanyName())
                .invoices(companyDto.getInvoiceIdList().stream().map(id -> invoiceService.getInvoice(id).orElse(null)).collect(Collectors.toList()))
                .build();
        return company;
    }

    public List<CompanyDto> mapCompanyListToCompanyDtosList(final List<Company> companies) {
        return companies.stream()
                .map(company -> mapCompanyToCompanyDto(company))
                .collect(Collectors.toList());
    }

    public List<Company> mapCompanyDtosListToCompanyList(final List<CompanyDto> companyDtos) {
        return companyDtos.stream()
                .map(companyDto -> mapCompanyDtoToCompany(companyDto))
                .collect(Collectors.toList());
    }
}
