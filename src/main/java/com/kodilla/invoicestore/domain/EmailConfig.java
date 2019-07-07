package com.kodilla.invoicestore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "email_configs")
public class EmailConfig {
    @Id
    @GeneratedValue
    @Column(name = "email_config_id")
    @NotNull
    private Long emailConfigId;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "smtp_server")
    private String smtpServer;

    @Column(name = "smtp_port")
    private int smtpPort;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_auth_req")
    private boolean isAuthReq;

    @Column(name = "encryption_type")
    @Enumerated(EnumType.ORDINAL)
    private EncryptionType encryptionType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
