package com.example.ServiceTest.Repository;

import com.example.ServiceTest.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Customer c " +
            "WHERE c.phone_number = ?1"
    )
    Boolean selectExistsByPhoneNumber(String phone_number);

}
