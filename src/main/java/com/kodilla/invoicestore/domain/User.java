package com.kodilla.invoicestore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name = "login")
    private String login;

    @Column(name = "taxid")
    private String taxId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @OneToOne(targetEntity = EmailConfig.class,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    @JoinColumn(name = "email_config_id")
    private EmailConfig emailConfig;

    @OneToMany(targetEntity = Invoice.class,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Invoice> invoices = new ArrayList<>();
}
