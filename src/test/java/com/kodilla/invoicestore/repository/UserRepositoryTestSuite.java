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


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserRepositoryTestSuite {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailConfigRepository emailConfigRepository;

    private Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTestSuite.class);


    @Test
    public void testSaveOne() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId("6781230987l");
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");
        //When
        userRepository.save(johnSmith);
        long johnSmithId = johnSmith.getUserId();

        //Then
        Assert.assertNotEquals(0, johnSmithId);

        //Cleanup
        try {
            userRepository.delete(johnSmith);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveThree() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId("6781230987l");
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId("1236547809l");
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId("1234567890l");
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);
        long userCount = userRepository.count();

        //Then
        Assert.assertEquals(3, userCount);

        //Cleanup
        try {
            userRepository.delete(johnSmith);
            userRepository.delete(janeDoe);
            userRepository.delete(clarkKent);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testFindAll() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId("6781230987l");
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId("1236547809l");
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId("1234567890l");
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);
        List<User> users = userRepository.findAll();

        //Then
        Assert.assertEquals(3, users.size());

        //Cleanup
        try {
            userRepository.deleteAll(users);
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testDeleteById() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId("6781230987l");
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId("1236547809l");
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId("1234567890l");
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);

        long janeDoeId = janeDoe.getUserId();

        userRepository.deleteById(janeDoeId);
        List<User> users = userRepository.findAll();


        //Then
        Assert.assertEquals(2, users.size());


        //Cleanup
        try {
            userRepository.deleteAll(users);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }


    }

    @Test
    public void testFindById() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId("6781230987l");
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId("1236547809l");
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId("1234567890l");
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userRepository.save(johnSmith);
        userRepository.save(janeDoe);
        userRepository.save(clarkKent);

        long johnSmithId = johnSmith.getUserId();
        long janeDoeId = janeDoe.getUserId();
        long clarkKentId = clarkKent.getUserId();

        User foundUser1 = userRepository.findById(johnSmithId).get();
        User foundUser2 = userRepository.findById(janeDoeId).get();
        User foundUser3 = userRepository.findById(clarkKentId).get();

        //Then
        Assert.assertEquals("jsmith", foundUser1.getLogin());
        Assert.assertEquals("jdoe", foundUser2.getLogin());
        Assert.assertEquals("ckent", foundUser3.getLogin());

        //Cleanup
        try {
            userRepository.delete(johnSmith);
            userRepository.delete(janeDoe);
            userRepository.delete(clarkKent);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testSaveUserWithEmailConfig() {
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
        long johnSmithId = johnSmith.getUserId();

        //Then
        Assert.assertEquals(1, userRepository.count());
        Assert.assertEquals("john.smith@domain1.com", userRepository.findById(johnSmithId).get().getEmailConfig().getEmailAddress());

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