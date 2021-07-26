package com.example.ServiceTest.SampleData;

import com.example.ServiceTest.Entity.Customer;
import com.example.ServiceTest.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner CustomerCommandLineRunner(
            CustomerRepository repository){
        return args ->{
            Customer Minh = new Customer(
                    1L,
                    "Minh",
                    "Long Bien, Ha Noi",
                    "0901779611"
            );
            Customer Nam = new Customer(
                    2L,
                    "Nam",
                    "Dong Anh, Ha Noi",
                    "0123456789"
            );
            repository.saveAll(
                    List.of(Minh, Nam)
            );
        };
    }
}

