package com.example.ServiceTest.Customer;

import com.example.ServiceTest.Entity.Customer;
import com.example.ServiceTest.Repository.CustomerRepository;
import com.example.ServiceTest.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock private CustomerRepository customerRepository;
    private CustomerService undertest;


    @BeforeEach
    void setUp(){
        undertest = new CustomerService(customerRepository);
    }

    @Test
    void canGetCustomers() {
        //when
        undertest.getCustomers();
        //then
        verify(customerRepository).findAll();
    }

    @Test
    void canAddCustomer() {
        //given
        Customer customer = new Customer(
                2L,
                "Minh",
                "Long Bien, Ha Noi",
                "+84901779611"
        );
        //when
        undertest.addNewCustomer(customer);
        //then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();
        assertThat(capturedCustomer).isEqualTo(customer);

    }

    @Test
    void willThrowPhoneNumberIsTaken() {
        //given
        Customer customer = new Customer(
            2L,
            "Minh",
            "Long Bien, Ha Noi",
            "+84901779611"
        );
        given(customerRepository.selectExistsByPhoneNumber(customer.getPhone_number()))
                .willReturn(true);
        //when
        //then
        assertThatThrownBy(()->undertest.addNewCustomer(customer))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Customer exists !");
        verify(customerRepository, never()).save(any());
    }

}