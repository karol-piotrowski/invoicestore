package com.kodilla.invoicestore.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NbpConfig {
    @Value("${nbp.api.endpoint.prod}")
    private String nbpApiEndpoint;

}
