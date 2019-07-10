package com.kodilla.invoicestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpCurrencyDto {

    @JsonProperty("code")
    private String currency;

    @JsonProperty("rates")
    private NbpRatesDto[] rates;

    @Override
    public String toString() {
        return "NbpCurrencyDto{" +
                "currency=" + currency +
                ", rates=" + Arrays.toString(rates) +
                '}';
    }
}
