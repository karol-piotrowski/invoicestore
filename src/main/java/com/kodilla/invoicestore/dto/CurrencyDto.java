package com.kodilla.invoicestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.invoicestore.domain.CurrencyEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {

    private Long currencyId;

    private CurrencyEnum currency;

    private Double exchangeRate;

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "currencyId=" + currencyId +
                ", currency=" + currency +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
