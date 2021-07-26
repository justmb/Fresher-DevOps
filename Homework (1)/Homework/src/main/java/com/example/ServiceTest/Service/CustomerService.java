package com.example.ServiceTest.Service;

import com.example.ServiceTest.Repository.CustomerRepository;
import com.example.ServiceTest.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }


    public void addNewCustomer(Customer customer) {
        boolean existByPhoneNumber = customerRepository
                .selectExistsByPhoneNumber(customer.getPhone_number());
        if(existByPhoneNumber){
            throw new IllegalStateException(
                    "Customer exists !"
             );
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        boolean exist = customerRepository.existsById(customerId);
        if(!exist){
            throw new IllegalStateException("The customer with " + customerId + " not exist");
        }else{
            customerRepository.deleteById(customerId);
        }
    }

    public void updateCustomer(Long customerId, String name, String address, String phone_number) {
            Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new IllegalStateException(
                    "The customer with " + customerId + " not exist"
            ));
            if(name!=null
                && name.length() > 0
                && !Objects.equals(customer.getName(),name)){
                customer.setName(name);
            }
        if(address!=null
                && address.length() > 0
                && !Objects.equals(customer.getAddress(),address)){
            customer.setAddress(address);
        }
        if(phone_number!=null
                && !Objects.equals(customer.getPhone_number(),phone_number)){
            customer.setPhone_number(phone_number);
        }



    }
}
