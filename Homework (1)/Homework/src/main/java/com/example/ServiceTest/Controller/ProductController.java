package com.example.ServiceTest.Controller;


import com.example.ServiceTest.Entity.Product;
import com.example.ServiceTest.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")

@Transactional
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProduct();
    }

    @PostMapping
    public void addProduct(@RequestBody(required = false) Product product){
        productService.addProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable Long productId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) LocalDate date,
                              @RequestParam(required = false) String quantity){
        productService.updateProduct(productId, name, date, quantity);
    }
}
