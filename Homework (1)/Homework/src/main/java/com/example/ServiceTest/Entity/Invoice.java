package com.example.ServiceTest.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Invoice {

    @Id
    @SequenceGenerator(
        name = "invoice_sequence",
        sequenceName = "invoice_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "invoice_sequence"
    )

    private Long invoiceId;
    private LocalDate date;
    private Long price;
    private int table_number;
    public Invoice() {
    }

    public Invoice(Long invoiceId, LocalDate date, Long price, int table_number) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.price = price;
        this.table_number = table_number;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getTable_number(){return table_number;}

    public void setTable_number(int table_number){ this.table_number=table_number;}


    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", date=" + date +
                ", price=" + price +
                ", table number=" + table_number +
                '}';
    }
}
