package com.kodilla.invoicestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.invoicestore.domain.EncryptionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
    private Long userId;

}
