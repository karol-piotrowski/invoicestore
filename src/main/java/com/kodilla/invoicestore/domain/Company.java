package com.kodilla.invoicestore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    @NotNull
    private Long companyId;

    @Column(name = "taxid")
    private String taxId;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(targetEntity = Invoice.class,
    mappedBy = "company",
    fetch = FetchType.LAZY)
    private List<Invoice> invoices = new ArrayList<>();
}
