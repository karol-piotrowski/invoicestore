package com.kodilla.invoicestore.controller;

import com.kodilla.invoicestore.dto.InvoiceDto;
import com.kodilla.invoicestore.mapper.InvoiceMapper;
import com.kodilla.invoicestore.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<InvoiceDto> getInvoices() {
        return invoiceMapper.mapInvoicesListToInvoiceDtosList(invoiceService.getInvoices());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{invoiceId}")
    public InvoiceDto getInvoice(@PathVariable Long invoiceId) {
        return invoiceMapper.mapInvoiceToInvoiceDto(invoiceService.getInvoice(invoiceId).get());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{invoiceId}")
    public void deleteInvoice(@PathVariable Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/")
    public InvoiceDto updateInvoice(@RequestBody InvoiceDto invoiceDto) {
        return invoiceMapper.mapInvoiceToInvoiceDto(invoiceService.saveInvoice(invoiceMapper.mapInvoiceDtoToInvoice(invoiceDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE)
    public void createInvoice(@RequestBody InvoiceDto invoiceDto) {
        invoiceService.saveInvoice(invoiceMapper.mapInvoiceDtoToInvoice(invoiceDto));
    }
}
