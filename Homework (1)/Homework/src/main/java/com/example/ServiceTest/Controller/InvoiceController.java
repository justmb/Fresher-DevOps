package com.example.ServiceTest.Controller;

import com.example.ServiceTest.Entity.Invoice;
import com.example.ServiceTest.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getInvoices(){
        return invoiceService.getInvoice();
    }

    @PostMapping
    public void addInvoice(@RequestBody(required = false) Invoice invoice){
        invoiceService.addInvoice(invoice);
    }

    @DeleteMapping(path = "{invoiceId}")
    public void deleteInvoice(@PathVariable Long invoiceId){
        invoiceService.deleteInvoice(invoiceId);
    }

    @PutMapping(path = "{invoiceId}")
    public void updateInvoice(@PathVariable Long invoiceId,
                              @RequestParam(required = false)LocalDate date,
                              @RequestParam(required = false)Long price){
        invoiceService.updateInvoice(invoiceId, date, price);
    }
}
