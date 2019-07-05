package com.kodilla.invoicestore.controller;

import com.kodilla.invoicestore.dto.EmailConfigDto;
import com.kodilla.invoicestore.mapper.EmailConfigMapper;
import com.kodilla.invoicestore.service.EmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/emailconfigs")
public class EmailConfigController {
    @Autowired
    private EmailConfigService emailConfigService;
    
    @Autowired
    private EmailConfigMapper emailConfigMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<EmailConfigDto> getUsers() {
        return emailConfigMapper.mapEmailConfigsToEmailConfigDtos(emailConfigService.getEmailConfigs());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{emailConfigId}")
    public EmailConfigDto getUser(@PathVariable Long emailConfigId) {
        return emailConfigMapper.mapEmailConfigToEmailConfigDto(emailConfigService.getEmailConfig(emailConfigId).get());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{emailConfigId}")
    public void deleteUser(@PathVariable Long emailConfigId) {
        emailConfigService.deleteEmailConfig(emailConfigId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public EmailConfigDto updateUser(@RequestBody EmailConfigDto emailConfigDto) {
        return emailConfigMapper.mapEmailConfigToEmailConfigDto(emailConfigService.saveEmailConfig(emailConfigMapper.mapEmailConfigDtoToEmailConfig(emailConfigDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody EmailConfigDto emailConfigDto) {
        emailConfigService.saveEmailConfig(emailConfigMapper.mapEmailConfigDtoToEmailConfig(emailConfigDto));
    }
}
