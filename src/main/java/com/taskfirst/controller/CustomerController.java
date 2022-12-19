package com.taskfirst.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskfirst.entity.Customer;
import com.taskfirst.repository.MyCustomerRepo;

@RestController
//@ComponentScan(basePackages= {"com.taskfirst"})

public class CustomerController {
	@Autowired
	MyCustomerRepo customerRepo;
	@PostMapping("/customers")

public ResponseEntity<Customer> save(@RequestBody Customer customer){
		
		try{
			
			return new ResponseEntity<>(customerRepo.save(customer),HttpStatus.CREATED);
		}	catch (Exception e) {
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		try {
			List<Customer> List =customerRepo.findAll();
			if(List.isEmpty() ||List.size()==0) {
				return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Customer>>(List,HttpStatus.OK);
		
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getSingleCustomer(@PathVariable Long id){
		Optional<Customer> customer = customerRepo.findById(id);
			if(customer.isPresent()) {
				return new ResponseEntity<Customer>(customer.get(),HttpStatus.OK);
			}
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
}
}
