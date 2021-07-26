package com.example.ServiceTest.Product;

import com.example.ServiceTest.Entity.Product;
import com.example.ServiceTest.Repository.ProductRepository;
import com.example.ServiceTest.Service.ProductService;
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
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService undertest;


    @BeforeEach
    void setUp() {
        undertest = new ProductService(productRepository);
    }

    @Test
    void canGetProduct() {
        //when
        undertest.getProduct();
        //then
        verify(productRepository).findAll();
    }

    @Test
    void canAddInvoice() {
        //given
        Product product = new Product(
                1L,
                "Bread",
                LocalDate.of(2021, Month.DECEMBER,24),
                "35"
        );
        //when
        undertest.addProduct(product);
        //then
        ArgumentCaptor<Product> customerArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(customerArgumentCaptor.capture());
        Product capturedProduct = customerArgumentCaptor.getValue();
        assertThat(capturedProduct).isEqualTo(product);

    }

    @Test
    void willThrowInvoiceExists() {
        //given
        Product product = new Product(
                1L,
                "Bread",
                LocalDate.of(2021, Month.DECEMBER,24),
                "35"
        );
        given(productRepository.existsById(product.getProductId()))
                .willReturn(true);
        //when
        //then
        assertThatThrownBy(() -> undertest.addProduct(product))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Product exists !");
        verify(productRepository, never()).save(any());
    }
}