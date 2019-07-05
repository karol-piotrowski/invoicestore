package com.kodilla.invoicestore.mapper;

import com.kodilla.invoicestore.domain.EmailConfig;
import com.kodilla.invoicestore.dto.EmailConfigDto;
import com.kodilla.invoicestore.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@NoArgsConstructor
public class EmailConfigMapper {
    @Autowired
    private UserService userService;

    public EmailConfigDto mapEmailConfigToEmailConfigDto(final EmailConfig emailConfig) {
        EmailConfigDto emailConfigDto = EmailConfigDto.builder()
                .emailConfigId(emailConfig.getEmailConfigId())
                .emailAddress(emailConfig.getEmailAddress())
                .smtpServer(emailConfig.getSmtpServer())
                .smtpPort(emailConfig.getSmtpPort())
                .username(emailConfig.getUsername())
                .password(emailConfig.getPassword())
                .isAuthReq(emailConfig.isAuthReq())
                .encryptionType(emailConfig.getEncryptionType())
                .build();
        if(!isNull(emailConfig.getUser())) {
            emailConfigDto.setUserId(emailConfig.getUser().getUserId());
        }
        return emailConfigDto;
    }

    public EmailConfig mapEmailConfigDtoToEmailConfig(final EmailConfigDto emailConfigDto) {
        EmailConfig emailConfig = EmailConfig.builder()
                .emailConfigId(emailConfigDto.getEmailConfigId())
                .emailAddress(emailConfigDto.getEmailAddress())
                .smtpServer(emailConfigDto.getSmtpServer())
                .smtpPort(emailConfigDto.getSmtpPort())
                .username(emailConfigDto.getUsername())
                .password(emailConfigDto.getPassword())
                .isAuthReq(emailConfigDto.isAuthReq())
                .encryptionType(emailConfigDto.getEncryptionType())
                .build();
        if(!isNull(emailConfigDto.getUserId())) {
            emailConfig.setUser(userService.getUser(emailConfigDto.getUserId()).orElse(null));
        }
        return emailConfig;
    }

    public List<EmailConfigDto> mapEmailConfigsToEmailConfigDtos(final List<EmailConfig> emailConfigs) {
        return emailConfigs.stream()
                .map(emailConfig -> mapEmailConfigToEmailConfigDto(emailConfig))
                .collect(Collectors.toList());
    }

    public List<EmailConfig> mapEmailConfigsDtosToEmailConfigs(final List<EmailConfigDto> emailConfigDtos) {
        return emailConfigDtos.stream()
                .map(emailConfigDto -> mapEmailConfigDtoToEmailConfig(emailConfigDto))
                .collect(Collectors.toList());
    }
}
