package com.taskfirst.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskfirst.entity.Customer;
@Repository

public interface MyCustomerRepo extends JpaRepository<Customer, Long> {

}
