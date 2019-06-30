package com.kodilla.invoicestore.service;

import com.kodilla.invoicestore.domain.EmailConfig;
import com.kodilla.invoicestore.domain.EncryptionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class EmailConfigServiceTestSuite {
    @Autowired
    EmailConfigService emailConfigService;

    private Logger LOGGER = LoggerFactory.getLogger(EmailConfigServiceTestSuite.class);


    @Test
    public void testSaveOneEmailConfig() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("mail1@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("mail1@domain1.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);

        //When
        emailConfigService.saveEmailConfig(emailConfig1);
        long emailConfig1Id = emailConfig1.getEmailConfigId();

        //Then
        assertNotEquals(0, emailConfig1Id);

        //Cleanup
        try {
            emailConfigService.deleteEmailConfig(emailConfig1.getEmailConfigId());
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }

    }

    @Test
    public void testSaveThreeEmailConfigs() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("mail1@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("mail1@domain1.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);

        EmailConfig emailConfig2 = new EmailConfig();
        emailConfig2.setEmailAddress("mail2@domain2.com");
        emailConfig2.setSmtpServer("smtp.domain2.com");
        emailConfig2.setSmtpPort(487);
        emailConfig2.setUsername("mail2@domain2.com");
        emailConfig2.setPassword("PaSsWoRd");
        emailConfig2.setAuthReq(true);
        emailConfig2.setEncryptionType(EncryptionType.STARTTLS);

        EmailConfig emailConfig3 = new EmailConfig();
        emailConfig3.setEmailAddress("mail3@domain3.com");
        emailConfig3.setSmtpServer("smtp.domain3.com");
        emailConfig3.setSmtpPort(487);
        emailConfig3.setUsername("mail3@domain3.com");
        emailConfig3.setPassword("PASSword");
        emailConfig3.setAuthReq(true);
        emailConfig3.setEncryptionType(EncryptionType.SSL);

        //When
        emailConfigService.saveEmailConfig(emailConfig1);
        emailConfigService.saveEmailConfig(emailConfig2);
        emailConfigService.saveEmailConfig(emailConfig3);
        long emailConfigCount = emailConfigService.count();

        //Then
        assertEquals(3, emailConfigCount);

        //Cleanup
        try {
            emailConfigService.deleteEmailConfig(emailConfig1.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig2.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig3.getEmailConfigId());

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testGetEmailConfigs() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("mail1@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("mail1@domain1.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);

        EmailConfig emailConfig2 = new EmailConfig();
        emailConfig2.setEmailAddress("mail2@domain2.com");
        emailConfig2.setSmtpServer("smtp.domain2.com");
        emailConfig2.setSmtpPort(487);
        emailConfig2.setUsername("mail2@domain2.com");
        emailConfig2.setPassword("PaSsWoRd");
        emailConfig2.setAuthReq(true);
        emailConfig2.setEncryptionType(EncryptionType.STARTTLS);

        EmailConfig emailConfig3 = new EmailConfig();
        emailConfig3.setEmailAddress("mail3@domain3.com");
        emailConfig3.setSmtpServer("smtp.domain3.com");
        emailConfig3.setSmtpPort(487);
        emailConfig3.setUsername("mail3@domain3.com");
        emailConfig3.setPassword("PASSword");
        emailConfig3.setAuthReq(true);
        emailConfig3.setEncryptionType(EncryptionType.SSL);

        //When
        emailConfigService.saveEmailConfig(emailConfig1);
        emailConfigService.saveEmailConfig(emailConfig2);
        emailConfigService.saveEmailConfig(emailConfig3);

        List<EmailConfig> emailConfigs = emailConfigService.getEmailConfigs();

        //Then
        assertEquals(3, emailConfigs.size());

        //Cleanup
        try {
            emailConfigService.deleteEmailConfig(emailConfig1.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig2.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig3.getEmailConfigId());
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testGetEmailConfig() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("mail1@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("mail1@domain.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);

        EmailConfig emailConfig2 = new EmailConfig();
        emailConfig2.setEmailAddress("mail2@domain2.com");
        emailConfig2.setSmtpServer("smtp.domain2.com");
        emailConfig2.setSmtpPort(487);
        emailConfig2.setUsername("mail2@domain.com");
        emailConfig2.setPassword("PaSsWoRd");
        emailConfig2.setAuthReq(true);
        emailConfig2.setEncryptionType(EncryptionType.STARTTLS);

        EmailConfig emailConfig3 = new EmailConfig();
        emailConfig3.setEmailAddress("mail3@domain3.com");
        emailConfig3.setSmtpServer("smtp.domain3.com");
        emailConfig3.setSmtpPort(487);
        emailConfig3.setUsername("mail3@domain.com");
        emailConfig3.setPassword("PASSword");
        emailConfig3.setAuthReq(true);
        emailConfig3.setEncryptionType(EncryptionType.SSL);

        //When
        emailConfigService.saveEmailConfig(emailConfig1);
        emailConfigService.saveEmailConfig(emailConfig2);
        emailConfigService.saveEmailConfig(emailConfig3);

        long emailConfig1Id = emailConfig1.getEmailConfigId();
        long emailConfig2Id = emailConfig2.getEmailConfigId();
        long emailConfig3Id = emailConfig3.getEmailConfigId();

        EmailConfig foundEmailConfig1 = emailConfigService.getEmailConfig(emailConfig1Id).get();
        EmailConfig foundEmailConfig2 = emailConfigService.getEmailConfig(emailConfig2Id).get();
        EmailConfig foundEmailConfig3 = emailConfigService.getEmailConfig(emailConfig3Id).get();

        //Then
        assertEquals("mail1@domain1.com", foundEmailConfig1.getEmailAddress());
        assertEquals("mail2@domain2.com", foundEmailConfig2.getEmailAddress());
        assertEquals("mail3@domain3.com", foundEmailConfig3.getEmailAddress());

        //Cleanup
        try {
            emailConfigService.deleteEmailConfig(emailConfig1.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig2.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig3.getEmailConfigId());

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testDeleteEmailConfig() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("mail1@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("mail1@domain.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);

        EmailConfig emailConfig2 = new EmailConfig();
        emailConfig2.setEmailAddress("mail2@domain2.com");
        emailConfig2.setSmtpServer("smtp.domain2.com");
        emailConfig2.setSmtpPort(487);
        emailConfig2.setUsername("mail2@domain2.com");
        emailConfig2.setPassword("PaSsWoRd");
        emailConfig2.setAuthReq(true);
        emailConfig2.setEncryptionType(EncryptionType.STARTTLS);

        EmailConfig emailConfig3 = new EmailConfig();
        emailConfig3.setEmailAddress("mail3@domain3.com");
        emailConfig3.setSmtpServer("smtp.domain3.com");
        emailConfig3.setSmtpPort(487);
        emailConfig3.setUsername("mail3@domain3.com");
        emailConfig3.setPassword("PASSword");
        emailConfig3.setAuthReq(true);
        emailConfig3.setEncryptionType(EncryptionType.SSL);

        //When
        emailConfigService.saveEmailConfig(emailConfig1);
        emailConfigService.saveEmailConfig(emailConfig2);
        emailConfigService.saveEmailConfig(emailConfig3);

        long emailConfig2Id = emailConfig2.getEmailConfigId();

        emailConfigService.deleteEmailConfig(emailConfig2Id);
        List<EmailConfig> emailConfigs = emailConfigService.getEmailConfigs();

        //Then
        assertEquals(2, emailConfigs.size());

        //Cleanup
        try {
            emailConfigService.deleteEmailConfig(emailConfig1.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig2.getEmailConfigId());
            emailConfigService.deleteEmailConfig(emailConfig3.getEmailConfigId());
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testCount() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("mail1@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("mail1@domain1.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);

        //When
        emailConfigService.saveEmailConfig(emailConfig1);
        long emailConfig1Id = emailConfig1.getEmailConfigId();

        //Then
        assertNotEquals(0, emailConfig1Id);

        //Cleanup
        try {
            emailConfigService.deleteEmailConfig(emailConfig1.getEmailConfigId());
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }
}