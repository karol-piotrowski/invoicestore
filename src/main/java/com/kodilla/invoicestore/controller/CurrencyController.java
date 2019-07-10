package com.kodilla.invoicestore.controller;

import com.kodilla.invoicestore.domain.CurrencyEnum;
import com.kodilla.invoicestore.dto.CurrencyDto;
import com.kodilla.invoicestore.mapper.CurrencyMapper;
import com.kodilla.invoicestore.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/currencies")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyMapper currencyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<CurrencyDto> getCurrencies() {
        return currencyMapper.mapCurrencyListToCurrencyDtosList(currencyService.getCurrencies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{currencyEnum}")
    public CurrencyDto getCurrencyBySymbol(@PathVariable CurrencyEnum currencyEnum) {
        return currencyMapper.mapCurrencyToCurrencyDto(currencyService.getCurrency(currencyEnum));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/refresh")
    public List<CurrencyDto> refreshCurrencies() {
        currencyService.refreshRates();
        return currencyMapper.mapCurrencyListToCurrencyDtosList(currencyService.getCurrencies());
    }
}
