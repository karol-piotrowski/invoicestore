package com.kodilla.invoicestore.service;

import com.kodilla.invoicestore.client.NbpClient;
import com.kodilla.invoicestore.domain.Currency;
import com.kodilla.invoicestore.domain.CurrencyEnum;
import com.kodilla.invoicestore.dto.NbpCurrencyDto;
import com.kodilla.invoicestore.mapper.CurrencyMapper;
import com.kodilla.invoicestore.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private NbpClient nbpClient;

    @Autowired
    private CurrencyMapper currencyMapper;

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getCurrency(Long id) {
        return currencyRepository.findById(id);
    }

    public Currency getCurrency(CurrencyEnum currencyEnum) {
        return currencyRepository.findByCurrency(currencyEnum);
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    public long count() {
        return currencyRepository.count();
    }

    public void refreshRates() {
        List<NbpCurrencyDto> nbpCurrencyDtos = nbpClient.getCurrencyExchangeRates();
        Currency pln = Currency.builder()
                .currency(CurrencyEnum.PLN)
                .exchangeRate(1.0)
                .build();
        try {
            pln.setCurrencyId(getCurrency(CurrencyEnum.PLN).getCurrencyId());
        } catch (NullPointerException e) {
            pln.setCurrencyId(null);
        }
        saveCurrency(pln);
        nbpCurrencyDtos.stream()
                .map(nbpCurrencyDto -> currencyMapper.mapCurrecyDtoToCurrency(currencyMapper.mapNbpCurrencyDtoToCurrencyDto(nbpCurrencyDto)))
                .forEach(this::saveCurrency);
    }
}
