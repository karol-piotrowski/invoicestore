package com.kodilla.invoicestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDto {
    private Long companyId;
    private String taxId;
    private String companyName;
    private List<Long> invoiceIdList = new ArrayList<>();

}
