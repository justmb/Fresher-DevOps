package com.example.ServiceTest.Invoice;


import com.example.ServiceTest.Entity.Invoice;
import com.example.ServiceTest.Repository.InvoiceRepository;
import com.example.ServiceTest.Service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
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
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    private InvoiceService undertest;


    @BeforeEach
    void setUp() {
        undertest = new InvoiceService(invoiceRepository);
    }

    @Test
    void canGetInvoice() {
        //when
        undertest.getInvoice();
        //then
        verify(invoiceRepository).findAll();
    }

    @Test
    void canAddInvoice() {
        //given
        Invoice invoice1 = new Invoice(
                1L,
                LocalDate.of(2021, Month.JUNE, 22),
                300L,
                5
        );
        //when
        undertest.addInvoice(invoice1);
        //then
        ArgumentCaptor<Invoice> customerArgumentCaptor = ArgumentCaptor.forClass(Invoice.class);
        verify(invoiceRepository).save(customerArgumentCaptor.capture());
        Invoice capturedInvoice = customerArgumentCaptor.getValue();
        assertThat(capturedInvoice).isEqualTo(invoice1);

    }

    @Test
    void willThrowInvoiceExists() {
        //given
        Invoice invoice1 = new Invoice(
                1L,
                LocalDate.of(2021, Month.JUNE, 22),
                300L,
                5
        );
        given(invoiceRepository.existsById(invoice1.getInvoiceId()))
                .willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> undertest.addInvoice(invoice1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Invoice exists !");
        verify(invoiceRepository, never()).save(any());
    }
}