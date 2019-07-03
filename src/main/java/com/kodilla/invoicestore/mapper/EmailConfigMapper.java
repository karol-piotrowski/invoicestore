package com.kodilla.invoicestore.mapper;

import com.kodilla.invoicestore.domain.EmailConfig;
import com.kodilla.invoicestore.dto.EmailConfigDto;
import com.kodilla.invoicestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class EmailConfigMapper {
    @Autowired
    private UserService userService;

    public EmailConfigDto mapEmailConfigToEmailConfigDto(final EmailConfig emailConfig) {
        return new EmailConfigDto(emailConfig.getEmailConfigId(),
                emailConfig.getEmailAddress(),
                emailConfig.getSmtpServer(),
                emailConfig.getSmtpPort(),
                emailConfig.getUsername(),
                emailConfig.getPassword(),
                emailConfig.isAuthReq(),
                emailConfig.getEncryptionType(),
                emailConfig.getUser().getUserId()
        );
    }

    public EmailConfig mapEmailConfigDtoToEmailConfig(final EmailConfigDto emailConfigDto) {
        return new EmailConfig(emailConfigDto.getEmailConfigId(),
                emailConfigDto.getEmailAddress(),
                emailConfigDto.getSmtpServer(),
                emailConfigDto.getSmtpPort(),
                emailConfigDto.getUsername(),
                emailConfigDto.getPassword(),
                emailConfigDto.isAuthReq(),
                emailConfigDto.getEncryptionType(),
                userService.getUser(emailConfigDto.getUserId()).get()
        );
    }

    public List<EmailConfigDto> mapEmailConfigsToEmailConfigDtos(final List<EmailConfig> emailConfigs) {
        return emailConfigs.stream()
                .map(emailConfig -> new EmailConfigDto(emailConfig.getEmailConfigId(),
                        emailConfig.getEmailAddress(),
                        emailConfig.getSmtpServer(),
                        emailConfig.getSmtpPort(),
                        emailConfig.getUsername(),
                        emailConfig.getPassword(),
                        emailConfig.isAuthReq(),
                        emailConfig.getEncryptionType(),
                        emailConfig.getUser().getUserId()))
                .collect(Collectors.toList());
    }

    public List<EmailConfig> mapEmailConfigsDtosToEmailConfigs(final List<EmailConfigDto> emailConfigDtos) {
        return emailConfigDtos.stream()
                .map(emailConfigDto -> new EmailConfig(emailConfigDto.getEmailConfigId(),
                        emailConfigDto.getEmailAddress(),
                        emailConfigDto.getSmtpServer(),
                        emailConfigDto.getSmtpPort(),
                        emailConfigDto.getUsername(),
                        emailConfigDto.getPassword(),
                        emailConfigDto.isAuthReq(),
                        emailConfigDto.getEncryptionType(),
                        userService.getUser(emailConfigDto.getUserId()).get()))
                .collect(Collectors.toList());
    }
}
