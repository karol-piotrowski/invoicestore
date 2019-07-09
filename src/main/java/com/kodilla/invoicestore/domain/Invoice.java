package com.kodilla.invoicestore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

//@NamedQueries({
//        @NamedQuery(
//                name = "Invoice.retrieveInvoicesByUserId",
//                query = "FROM Invoice WHERE User > 10"
//        ),
//        @NamedQuery(
//                name = "Task.retrieveShortTasks",
//                query = "FROM Task WHERE duration <= 10"
//        ),
//        @NamedQuery(
//                name = "Task.retrieveTasksWithDurationLongerThan",
//                query = "FROM Task WHERE duration > :DURATION"
//        )
//})

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
    @NotNull
    private Long invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "invoice_type")
    private InvoiceType invoiceType;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private CurrencyEnum currency;

    @Column(name = "file_address")
    private String fileAddress;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
