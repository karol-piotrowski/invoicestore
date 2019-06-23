package com.kodilla.invoicestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.invoicestore.domain.EncryptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailConfigDto {
    private Long emailConfigId;
    private String emailAddress;
    private String smtpServer;
    private int smtpPort;
    private String username;
    private String password;
    private boolean isAuthReq;
    private EncryptionType encryptionType;

}
