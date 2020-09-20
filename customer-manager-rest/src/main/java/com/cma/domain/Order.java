package com.cma.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cma.constants.OrderStatus;

@Entity
@Table(name = "ORDERS", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class Order extends AuditTrail {

	private static final long serialVersionUID = 4550636638678546748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;	

	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_STATUS", nullable = false, length = 20)
	private OrderStatus orderStatus;

	@Column(name = "PRODUCT_NAME", nullable = false, length = 255)
	private String productName;

	@Column(name = "PRODUCT_PRICE", nullable = false, length = 255)
	private Double productPrice;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}	

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}