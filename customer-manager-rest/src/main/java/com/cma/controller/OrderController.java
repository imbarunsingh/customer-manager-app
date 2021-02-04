package com.cma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cma.entity.Order;
import com.cma.exception.BaseException;
import com.cma.repository.OrderRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(description = "Operations about customer orders")
public class OrderController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderRepository orderRepository;

	@ApiOperation(value = "Get a list of order - API 1.0", response = Order.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", example = "0", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", example = "10", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
					+ "Default sort order is ascending. " + "Multiple sort criteria are supported.") })
	@GetMapping(value = "/v1/orders", produces = "application/json")
	public ResponseEntity<Map<String, Object>> getOrdersV1(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "orderStatus") String[] sort) {

		List<Order> orders = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		Page<Order> pageOrders = null;
		try {
			pageOrders = orderRepository.findAll(pageable);
		} catch (Exception ex) {
			logger.error("Exception occurred getting the order list in API 1.0 due to:: {}", ex);
			throw new BaseException("Database exception getting order records.", ex);
		}
		orders = pageOrders.getContent();
		if (orders.isEmpty()) {
			logger.info("No Orders found ::");
			throw new BaseException(HttpStatus.OK, "No orders record found.");
		}
		Map<String, Object> response = new HashMap<>();
		response.put("orders", orders);
		response.put("currentPage", pageOrders.getNumber());
		response.put("totalItems", pageOrders.getTotalElements());
		response.put("totalPages", pageOrders.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
