package com.example.ServiceTest.Entity;


import javax.persistence.*;

@Entity
@Table
public class Customer {

    @Id
    @SequenceGenerator(
            name ="customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )

    private Long customerId;
    private String name;
    private String address;
    private String phone_number;

    public Customer(Long customerId, String name, String address, String phone_number) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public Customer(){
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number=" + phone_number +
                '}';
    }
}
