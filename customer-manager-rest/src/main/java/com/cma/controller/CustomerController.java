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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cma.domain.Customer;
import com.cma.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "customer-manager", description = "Operations pertaining to customers")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerRepository customerRepository;

	@ApiOperation(value = "Get a list of customers - API 1.0", response = Customer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", example="0", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", example="10", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
					+ "Default sort order is ascending. " + "Multiple sort criteria are supported.") })
	@GetMapping(value = "/v1/customers", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getCustomersV1(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
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

	@ApiOperation(value = "Get a list of customers - API 2.0", response = Customer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@GetMapping(value = "/v2/customers", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getCustomersV2(@RequestParam(defaultValue = "0") int page,
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

	@ApiOperation(value = "Search customers based on criteria - API 1.0", response = Map.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@GetMapping(value = "/v1/search/{searchTerm}", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getCustomersBySearchTermV1(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "15") int size,
			@RequestParam(required = false, defaultValue = "firstName") String[] sort) {
		return null;
	}

}
