package com.example.ServiceTest.Controller;

import com.example.ServiceTest.Service.CustomerService;
import com.example.ServiceTest.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/customer")

@Transactional
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable Long customerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) String phone_number){
        customerService.updateCustomer(customerId, name, address, phone_number);
    }
}
