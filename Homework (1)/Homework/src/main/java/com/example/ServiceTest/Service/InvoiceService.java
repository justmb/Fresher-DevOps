package com.example.ServiceTest.Service;

import com.example.ServiceTest.Entity.Invoice;
import com.example.ServiceTest.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoice() {
        return invoiceRepository.findAll();
    }

    public void addInvoice(Invoice invoice) {
        boolean existById= invoiceRepository
                .existsById(invoice.getInvoiceId());
        if(existById){
            throw new IllegalStateException(
                    "Invoice exists !"
            );
        }
        invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long invoiceId) {
        boolean exist = invoiceRepository.existsById(invoiceId);
        if(!exist){
            throw new IllegalStateException(
                    "This invoice id " + invoiceId + " not exist"
            );
        }else{
            invoiceRepository.deleteById(invoiceId);
        }
    }

    public void updateInvoice(Long invoiceId, LocalDate date, Long price) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()->new IllegalStateException(
                "This invoice id " +invoiceId+" not exist"
        ));

        if(date!=null
           && !Objects.equals(invoice.getDate().getYear(),date.getYear())
           && !Objects.equals(invoice.getDate().getMonth(),date.getMonth())
        ){
            invoice.setDate(date);
        }

        if(price!=null
            && !Objects.equals(invoice.getPrice(),price)){
            invoice.setPrice(price);
        }
    }

}
