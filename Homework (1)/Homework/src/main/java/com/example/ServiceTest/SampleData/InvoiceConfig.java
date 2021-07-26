package com.example.ServiceTest.SampleData;


import com.example.ServiceTest.Entity.Invoice;
import com.example.ServiceTest.Repository.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class InvoiceConfig {
    @Bean
    CommandLineRunner InvoiceCommandLineRunner(
            InvoiceRepository repository) {
        return args -> {
            Invoice invoice1 = new Invoice(
                    1L,
                    LocalDate.of(2021, Month.JUNE, 22),
                    300L,
                    5

            );
            Invoice invoice2 = new Invoice(
                    2L,
                    LocalDate.of(2021, Month.SEPTEMBER, 22),
                    200L,
                    6
            );
            repository.saveAll(
                    List.of(invoice1, invoice2)
            );
        };
    }
}
