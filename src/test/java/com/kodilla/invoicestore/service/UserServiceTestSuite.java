package com.kodilla.invoicestore.service;

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
public class UserServiceTestSuite {
    @Autowired
    UserService userService;

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceTestSuite.class);


    @Test
    public void testSaveOne() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId(6781230987l);
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");
        //When
        userService.saveUser(johnSmith);
        long johnSmithId = johnSmith.getUserId();

        //Then
        Assert.assertNotEquals(0, johnSmithId);

        //Cleanup
        try {
            userService.deleteUser(johnSmith.getUserId());
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
        johnSmith.setTaxId(6781230987l);
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId(1236547809l);
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId(1234567890l);
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userService.saveUser(johnSmith);
        userService.saveUser(janeDoe);
        userService.saveUser(clarkKent);
        long userCount = userService.count();

        //Then
        Assert.assertEquals(3, userCount);

        //Cleanup
        try {
            userService.deleteUser(johnSmith.getUserId());
            userService.deleteUser(janeDoe.getUserId());
            userService.deleteUser(clarkKent.getUserId());

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testGetUsers() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId(6781230987l);
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId(1236547809l);
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId(1234567890l);
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userService.saveUser(johnSmith);
        userService.saveUser(janeDoe);
        userService.saveUser(clarkKent);
        List<User> users = userService.getUsers();

        //Then
        Assert.assertEquals(3, users.size());

        //Cleanup
        try {
            userService.deleteUser(johnSmith.getUserId());
            userService.deleteUser(janeDoe.getUserId());
            userService.deleteUser(clarkKent.getUserId());

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testGetUser() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId(6781230987l);
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId(1236547809l);
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId(1234567890l);
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userService.saveUser(johnSmith);
        userService.saveUser(janeDoe);
        userService.saveUser(clarkKent);

        long johnSmithId = johnSmith.getUserId();
        long janeDoeId = janeDoe.getUserId();
        long clarkKentId = clarkKent.getUserId();

        User foundUser1 = userService.getUser(johnSmithId).get();
        User foundUser2 = userService.getUser(janeDoeId).get();
        User foundUser3 = userService.getUser(clarkKentId).get();

        //Then
        Assert.assertEquals("jsmith", foundUser1.getLogin());
        Assert.assertEquals("jdoe", foundUser2.getLogin());
        Assert.assertEquals("ckent", foundUser3.getLogin());

        //Cleanup
        try {
            userService.deleteUser(johnSmith.getUserId());
            userService.deleteUser(janeDoe.getUserId());
            userService.deleteUser(clarkKent.getUserId());

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testDeleteUser() {
        //Given
        LOGGER.info("TEST: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        User johnSmith = new User();
        johnSmith.setLogin("jsmith");
        johnSmith.setTaxId(6781230987l);
        johnSmith.setFirstname("John");
        johnSmith.setLastname("Smith");

        User janeDoe = new User();
        janeDoe.setLogin("jdoe");
        janeDoe.setTaxId(1236547809l);
        janeDoe.setFirstname("Jane");
        janeDoe.setLastname("Doe");

        User clarkKent = new User();
        clarkKent.setLogin("ckent");
        clarkKent.setTaxId(1234567890l);
        clarkKent.setFirstname("Clark");
        clarkKent.setLastname("Kent");
        //When
        userService.saveUser(johnSmith);
        userService.saveUser(janeDoe);
        userService.saveUser(clarkKent);

        long janeDoeId = janeDoe.getUserId();

        userService.deleteUser(janeDoeId);
        List<User> users = userService.getUsers();


        //Then
        Assert.assertEquals(2, users.size());


        //Cleanup
        try {
            userService.deleteUser(johnSmith.getUserId());
            userService.deleteUser(clarkKent.getUserId());
            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }
}