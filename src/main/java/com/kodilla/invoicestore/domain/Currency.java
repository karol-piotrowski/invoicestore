package com.kodilla.invoicestore.domain;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private Long currencyId;

    @Column(name = "currency")
    private CurrencyEnum currency;

    @Column(name = "exchange_rate")
    private Double exchangeRate;
}
