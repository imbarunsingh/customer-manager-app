package com.cma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cma.domain.Customer;
import com.cma.repository.CustomerRepository;

@RestController
public class CustomerController extends BaseController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/v1/customers")
	public ResponseEntity<Map<String, Object>> getCustomersV1(@RequestParam(defaultValue = "0") int page,
															@RequestParam(defaultValue = "15") int size,
															@RequestParam(required = false, defaultValue = "firstName") String[] sort) {
		try {
			List<Customer> customers = new ArrayList<>();
			Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
			Page<Customer> pageCustomers = customerRepository.findAll(pageable);

			customers = pageCustomers.getContent();

			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("customers", customers);
			response.put("currentPage", pageCustomers.getNumber());
			response.put("totalItems", pageCustomers.getTotalElements());
			response.put("totalPages", pageCustomers.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/v2/customers")
	public ResponseEntity<Map<String, Object>> getCustomersV1(@RequestParam(defaultValue = "0") int page,
															@RequestParam(defaultValue = "10") int size) {
		try {
			List<Customer> customers = new ArrayList<>();
			Pageable pageable = PageRequest.of(page, size);
			Page<Customer> pageCustomers = customerRepository.findAll(pageable);

			customers = pageCustomers.getContent();

			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("customers", customers);
			response.put("currentPage", pageCustomers.getNumber());
			response.put("totalItems", pageCustomers.getTotalElements());
			response.put("totalPages", pageCustomers.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/v1/search/{searchTerm}")
	public ResponseEntity<Map<String, Object>> getCustomersBySearchTermV1(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "15") int size,
			@RequestParam(required = false, defaultValue = "firstName") String[] sort) {
				return null;		
	}

}
