package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.EmailConfig;
import com.kodilla.invoicestore.domain.EncryptionType;
import com.kodilla.invoicestore.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class EmailConfigRepositoryTestSuite {
    @Autowired
    EmailConfigRepository emailConfigRepository;

    @Autowired
    UserRepository userRepository;

    private Logger LOGGER = LoggerFactory.getLogger(EmailConfigRepositoryTestSuite.class);

    @Test
    public void testSaveOne() {
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
        emailConfigRepository.save(emailConfig1);
        long emailConfig1Id = emailConfig1.getEmailConfigId();

        //Then
        assertNotEquals(0, emailConfig1Id);

        //Cleanup
        try {
            emailConfigRepository.delete(emailConfig1);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveThree() {
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
        emailConfigRepository.save(emailConfig1);
        emailConfigRepository.save(emailConfig2);
        emailConfigRepository.save(emailConfig3);
        long emailConfigCount = emailConfigRepository.count();

        //Then
        assertEquals(3, emailConfigCount);

        //Cleanup
        try {
            emailConfigRepository.delete(emailConfig1);
            emailConfigRepository.delete(emailConfig2);
            emailConfigRepository.delete(emailConfig3);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testFindAll() {
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
        emailConfigRepository.save(emailConfig1);
        emailConfigRepository.save(emailConfig2);
        emailConfigRepository.save(emailConfig3);

        List<EmailConfig> emailConfigs = emailConfigRepository.findAll();

        //Then
        assertEquals(3, emailConfigs.size());

        //Cleanup
        try {
            emailConfigRepository.deleteAll(emailConfigs);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testFindById() {
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
        emailConfigRepository.save(emailConfig1);
        emailConfigRepository.save(emailConfig2);
        emailConfigRepository.save(emailConfig3);

        long emailConfig1Id = emailConfig1.getEmailConfigId();
        long emailConfig2Id = emailConfig2.getEmailConfigId();
        long emailConfig3Id = emailConfig3.getEmailConfigId();

        EmailConfig foundEmailConfig1 = emailConfigRepository.findById(emailConfig1Id).get();
        EmailConfig foundEmailConfig2 = emailConfigRepository.findById(emailConfig2Id).get();
        EmailConfig foundEmailConfig3 = emailConfigRepository.findById(emailConfig3Id).get();

        //Then
        assertEquals("mail1@domain1.com", foundEmailConfig1.getEmailAddress());
        assertEquals("mail2@domain2.com", foundEmailConfig2.getEmailAddress());
        assertEquals("mail3@domain3.com", foundEmailConfig3.getEmailAddress());

        //Cleanup
        try {
            emailConfigRepository.delete(emailConfig1);
            emailConfigRepository.delete(emailConfig2);
            emailConfigRepository.delete(emailConfig3);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }

    }

    @Test
    public void deleteById() {
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
        emailConfigRepository.save(emailConfig1);
        emailConfigRepository.save(emailConfig2);
        emailConfigRepository.save(emailConfig3);

        long emailConfig2Id = emailConfig2.getEmailConfigId();

        emailConfigRepository.deleteById(emailConfig2Id);
        List<EmailConfig> emailConfigs = emailConfigRepository.findAll();

        //Then
        assertEquals(2, emailConfigs.size());

        //Cleanup
        try {
            emailConfigRepository.deleteAll(emailConfigs);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveEmailConfigWithUser() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        EmailConfig emailConfig1 = new EmailConfig();
        emailConfig1.setEmailAddress("john.smith@domain1.com");
        emailConfig1.setSmtpServer("smtp.domain1.com");
        emailConfig1.setSmtpPort(487);
        emailConfig1.setUsername("john.smith@domain1.com");
        emailConfig1.setPassword("p@55w0rd");
        emailConfig1.setAuthReq(true);
        emailConfig1.setEncryptionType(EncryptionType.SSL);
        emailConfigRepository.save(emailConfig1);


        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId("6781230987l");
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");
        johnSmith.setEmailConfig(emailConfig1);

        emailConfig1.setUser(johnSmith);

        //When
        userRepository.save(johnSmith);
        emailConfigRepository.save(emailConfig1);
        long emailConfig1Id = emailConfig1.getEmailConfigId();

        //Then
        Assert.assertEquals("jsmith", emailConfigRepository.findById(emailConfig1Id).get().getUser().getLogin());

        //Cleanup
        try {
            emailConfigRepository.delete(emailConfig1);
            userRepository.delete(johnSmith);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }

    }

}