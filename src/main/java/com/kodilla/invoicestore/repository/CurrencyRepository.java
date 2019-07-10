package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.Currency;
import com.kodilla.invoicestore.domain.CurrencyEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    @Override
    List<Currency> findAll();

    @Override
    Currency save(Currency currency);

    @Override
    Optional<Currency> findById(Long currencyId);

    @Override
    void deleteById(Long currencyId);

    Currency findByCurrency(CurrencyEnum currencyEnum);
}
