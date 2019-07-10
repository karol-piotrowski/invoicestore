package com.kodilla.invoicestore.client;

import com.kodilla.invoicestore.config.NbpConfig;
import com.kodilla.invoicestore.domain.CurrencyEnum;
import com.kodilla.invoicestore.dto.CurrencyDto;
import com.kodilla.invoicestore.dto.NbpCurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class NbpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    @Autowired
    private NbpConfig nbpConfig;

    private RestTemplate restTemplate = new RestTemplate();

    public List<NbpCurrencyDto> getCurrencyExchangeRates() {
        List<NbpCurrencyDto> currencies = new ArrayList<>();
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
            String currencyCode = currencyEnum.toString().toUpperCase();
            if (!currencyCode.equals("PLN")) {
                URI url = UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + "/exchangerates/rates/A/" + currencyCode + "/?format=json")
                        .build().encode().toUri();
                try {
                    NbpCurrencyDto currencyResponse = restTemplate.getForObject(url, NbpCurrencyDto.class);
                    currencies.add(currencyResponse);
                } catch (RestClientException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return currencies;
    }
}
