package com.kodilla.invoicestore.scheduler;

import com.kodilla.invoicestore.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RefreshRatesScheduler {

    private Logger LOGGER = LoggerFactory.getLogger(RefreshRatesScheduler.class);

    @Autowired
    private CurrencyService currencyService;

    @Scheduled(cron = "0 0 9 * * *")
    public void refreshExchangeRates() {
        try {
            currencyService.refreshRates();
            LOGGER.info("Exchange rates refreshed");
        } catch (Exception e) {
            LOGGER.error("Error during exchange rates refresh: " + e.getMessage());
        }
    }

}
