package com.example.ServiceTest.SampleData;

import com.example.ServiceTest.Entity.Product;
import com.example.ServiceTest.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner ProductCo0mmandLineRunner(
            ProductRepository repository){
        return args ->{
            Product Bread = new Product(
                    1L,
                    "Bread",
                    LocalDate.of(2021, Month.DECEMBER,24),
                    "35"
            );
            Product Milk = new Product(
                    2L,
                    "Milk",
                    LocalDate.of(2021, Month.SEPTEMBER,12),
                    "30"
            );
            repository.saveAll(
                    List.of(Bread, Milk)
            );
        };
    }
}
