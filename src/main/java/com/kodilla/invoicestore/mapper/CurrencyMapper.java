package com.kodilla.invoicestore.mapper;

import com.kodilla.invoicestore.domain.Currency;
import com.kodilla.invoicestore.domain.CurrencyEnum;
import com.kodilla.invoicestore.dto.CurrencyDto;
import com.kodilla.invoicestore.dto.NbpCurrencyDto;
import com.kodilla.invoicestore.service.CurrencyService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CurrencyMapper {
    @Autowired
    private CurrencyService currencyService;

    public CurrencyDto mapNbpCurrencyDtoToCurrencyDto(final NbpCurrencyDto nbpCurrencyDto) {
        CurrencyEnum currencyEnum = Enum.valueOf(CurrencyEnum.class, nbpCurrencyDto.getCurrency());
        CurrencyDto currencyDto = CurrencyDto.builder()
                .currency(currencyEnum)
                .exchangeRate(nbpCurrencyDto.getRates()[0].getMid())
                .build();

        try {
            currencyDto.setCurrencyId(currencyService.getCurrency(currencyEnum).getCurrencyId());
        } catch (NullPointerException e) {
            currencyDto.setCurrencyId(null);
        }

        return currencyDto;
    }

    public CurrencyDto mapCurrencyToCurrencyDto(final Currency currency) {
        CurrencyDto currencyDto = CurrencyDto.builder()
                .currencyId(currency.getCurrencyId())
                .currency(currency.getCurrency())
                .exchangeRate(currency.getExchangeRate())
                .build();
        return currencyDto;
    }

    public Currency mapCurrecyDtoToCurrency(final CurrencyDto currencyDto) {
        Currency currency = Currency.builder()
                .currencyId(currencyDto.getCurrencyId())
                .currency(currencyDto.getCurrency())
                .exchangeRate(currencyDto.getExchangeRate())
                .build();
        return currency;
    }

    public List<CurrencyDto> mapCurrencyListToCurrencyDtosList(final List<Currency> currencies) {
        return currencies.stream()
                .map(currency -> mapCurrencyToCurrencyDto(currency))
                .collect(Collectors.toList());
    }

    public List<Currency> mapCurrecyDtosListToCurrencyList(final List<CurrencyDto> currencyDtos) {
        return currencyDtos.stream()
                .map(currencyDto -> mapCurrecyDtoToCurrency(currencyDto))
                .collect(Collectors.toList());
    }
}
