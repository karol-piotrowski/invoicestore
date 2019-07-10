package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.CurrencyEnum;
import com.kodilla.invoicestore.domain.Invoice;
import com.kodilla.invoicestore.domain.InvoiceType;
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
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class InvoiceRepositoryTestSuite {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    private Logger LOGGER = LoggerFactory.getLogger(InvoiceRepositoryTestSuite.class);

//    @Test
//    public void findAll() {
//    }
//
//    @Test
//    public void save() {
//    }
//
//    @Test
//    public void findById() {
//    }
//
//    @Test
//    public void deleteById() {
//    }

    @Test
    public void testFindInvoicesByUser() {
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

        userRepository.save(johnSmith);
        userRepository.save(janeDoe);

        Invoice inv1 = Invoice.builder()
                .invoiceNumber("INV/2019/1")
                .invoiceType(InvoiceType.COST)
                .issuedDate(LocalDate.of(2019, 2, 1))
                .description("First Invoice")
                .amount(123.45)
                .currency(CurrencyEnum.PLN)
                .user(johnSmith)
                .build();

        Invoice inv2 = Invoice.builder()
                .invoiceNumber("INV/2019/2")
                .invoiceType(InvoiceType.PROFIT)
                .issuedDate(LocalDate.of(2019, 2, 3))
                .description("Second Invoice")
                .amount(234.56)
                .currency(CurrencyEnum.EUR)
                .user(johnSmith)
                .build();

        Invoice inv3 = Invoice.builder()
                .invoiceNumber("INV/2019/3")
                .invoiceType(InvoiceType.COST)
                .issuedDate(LocalDate.of(2019, 2, 5))
                .description("Third Invoice")
                .amount(345.67)
                .currency(CurrencyEnum.CHF)
                .user(janeDoe)
                .build();

        invoiceRepository.save(inv1);
        invoiceRepository.save(inv2);
        invoiceRepository.save(inv3);

        johnSmith.getInvoices().add(inv1);
        johnSmith.getInvoices().add(inv2);
        janeDoe.getInvoices().add(inv3);

        userRepository.save(johnSmith);
        userRepository.save(janeDoe);

        //When
        List<Invoice> johnSmithInvoices = invoiceRepository.findInvoicesByUser(johnSmith);
        List<Invoice> janeDoeInvoices = invoiceRepository.findInvoicesByUser(janeDoe);

        //Then
        Assert.assertEquals(2, johnSmithInvoices.size());
        Assert.assertEquals(1, janeDoeInvoices.size());

        //Cleanup
        try {
            invoiceRepository.delete(inv1);
            invoiceRepository.delete(inv2);
            invoiceRepository.delete(inv3);
            userRepository.delete(johnSmith);
            userRepository.delete(janeDoe);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }

    @Test
    public void testFindInvoicesByUserAndIssuedDateBetween() {
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

        userRepository.save(johnSmith);
        userRepository.save(janeDoe);

        Invoice inv1 = Invoice.builder()
                .invoiceNumber("INV/2019/1")
                .invoiceType(InvoiceType.COST)
                .issuedDate(LocalDate.of(2019, 2, 1))
                .description("First Invoice")
                .amount(123.45)
                .currency(CurrencyEnum.PLN)
                .user(johnSmith)
                .build();

        Invoice inv2 = Invoice.builder()
                .invoiceNumber("INV/2019/2")
                .invoiceType(InvoiceType.PROFIT)
                .issuedDate(LocalDate.of(2019, 2, 3))
                .description("Second Invoice")
                .amount(234.56)
                .currency(CurrencyEnum.EUR)
                .user(johnSmith)
                .build();

        Invoice inv3 = Invoice.builder()
                .invoiceNumber("INV/2019/3")
                .invoiceType(InvoiceType.COST)
                .issuedDate(LocalDate.of(2019, 2, 5))
                .description("Third Invoice")
                .amount(345.67)
                .currency(CurrencyEnum.CHF)
                .user(janeDoe)
                .build();

        invoiceRepository.save(inv1);
        invoiceRepository.save(inv2);
        invoiceRepository.save(inv3);

        johnSmith.getInvoices().add(inv1);
        johnSmith.getInvoices().add(inv2);
        janeDoe.getInvoices().add(inv3);

        userRepository.save(johnSmith);
        userRepository.save(janeDoe);

        //When
        List<Invoice> johnSmithInvoices2To4Feb = invoiceRepository.findInvoicesByUserAndIssuedDateBetween(johnSmith, LocalDate.of(2019,2,2), LocalDate.of(2019,2,4));
        List<Invoice> janeDoeInvoices6FebTo4Mar = invoiceRepository.findInvoicesByUserAndIssuedDateBetween(johnSmith, LocalDate.of(2019,2,6), LocalDate.of(2019,3,4));
        List<Invoice> johnSmithInvoices1To5Feb = invoiceRepository.findInvoicesByUserAndIssuedDateBetween(johnSmith, LocalDate.of(2019,2,1), LocalDate.of(2019,2,5));


        //Then
        Assert.assertEquals(1, johnSmithInvoices2To4Feb.size());
        Assert.assertEquals(0, janeDoeInvoices6FebTo4Mar.size());
        Assert.assertEquals(2, johnSmithInvoices1To5Feb.size());

        //Cleanup
        try {
            invoiceRepository.delete(inv1);
            invoiceRepository.delete(inv2);
            invoiceRepository.delete(inv3);
            userRepository.delete(johnSmith);
            userRepository.delete(janeDoe);

            LOGGER.info("Cleanup successful");
        } catch (Exception e) {
            LOGGER.error("Error during cleanup");
        }
    }


}