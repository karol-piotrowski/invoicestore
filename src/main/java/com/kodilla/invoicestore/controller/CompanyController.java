package com.kodilla.invoicestore.controller;

import com.kodilla.invoicestore.dto.CompanyDto;
import com.kodilla.invoicestore.mapper.CompanyMapper;
import com.kodilla.invoicestore.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<CompanyDto> getCompanies() {
        return companyMapper.mapCompanyListToCompanyDtosList(companyService.getCompanies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}")
    public CompanyDto getCompany(@PathVariable Long companyId) {
        return companyMapper.mapCompanyToCompanyDto(companyService.getCompany(companyId).get());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{companyId}")
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public CompanyDto updateCompany(@RequestBody CompanyDto companyDto) {
        return companyMapper.mapCompanyToCompanyDto(companyService.saveCompany(companyMapper.mapCompanyDtoToCompany(companyDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public void createCompany(@RequestBody CompanyDto companyDto) {
        companyService.saveCompany(companyMapper.mapCompanyDtoToCompany(companyDto));
    }
}
