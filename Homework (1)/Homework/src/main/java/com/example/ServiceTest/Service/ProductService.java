package com.example.ServiceTest.Service;

import com.example.ServiceTest.Entity.Product;
import com.example.ServiceTest.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        boolean checkExistById= productRepository
                .existsById(product.getProductId());
        if(checkExistById){
            throw new IllegalStateException(
                    "Product exists !"
            );
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exist = productRepository.existsById(productId);
        if(!exist){
            throw new IllegalStateException("The product with id " + productId + " not exist");
        }
        productRepository.deleteById(productId);
    }

    public void updateProduct(Long productId, String name, LocalDate date, String quantity) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalStateException(
                "Product " + productId + " not exist"
        ));

        if(name!=null && name.length() > 0
            && !Objects.equals(product.getName(),name)){
            product.setName(name);
        }

        if(date!=null
                && !Objects.equals(product.getDate().getYear(),date.getYear())
                && !Objects.equals(product.getDate().getMonth(),date.getMonth())
        ){
            product.setDate(date);
        }
        if(quantity!=null
                && !Objects.equals(product.getQuantity(),quantity)){
            product.setQuantity(quantity);
        }
    }
}
