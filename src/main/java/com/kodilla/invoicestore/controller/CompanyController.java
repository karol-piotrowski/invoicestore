package com.kodilla.invoicestore.controller;

import com.kodilla.invoicestore.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
}
