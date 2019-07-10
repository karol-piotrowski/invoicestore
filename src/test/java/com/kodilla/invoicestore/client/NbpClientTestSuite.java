package com.kodilla.invoicestore.client;

import com.kodilla.invoicestore.dto.NbpCurrencyDto;
import com.kodilla.invoicestore.mapper.CurrencyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class NbpClientTestSuite {
    @Autowired
    private NbpClient nbpClient;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Test
    public void testGetCurrencyExchangeRates() {
        //Given
        //When
        List<NbpCurrencyDto> currencies  = nbpClient.getCurrencyExchangeRates();

        //Then
        assertEquals(10, currencies.size());
    }
}