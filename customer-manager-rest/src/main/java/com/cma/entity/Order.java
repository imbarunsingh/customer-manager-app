package com.cma.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cma.constants.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ORDERS", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
@ApiModel(description="All details about the customer orders")
@EntityListeners(AuditingEntityListener.class)
public class Order extends Auditable<String>  implements Serializable {

	private static final long serialVersionUID = 4550636638678546748L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@ApiModelProperty(hidden = true)
	private Long id;	

	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_STATUS", length = 20)
	@ApiModelProperty(notes = "Order Status", example = "Delivered", required = true)
	@NotNull
	private OrderStatus orderStatus;

	@Column(name = "PRODUCT_NAME", nullable = false, length = 255)
	@ApiModelProperty(notes = "Product Name", example = "Apple - Macbook", required = true)
	@NotBlank
	private String productName;

	@Column(name = "PRODUCT_PRICE", length = 255)
	@ApiModelProperty(notes = "Product Price", example = "$1200", required = true)
	@NotNull
	private Double productPrice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
	@ApiModelProperty(hidden = true)
	@JsonIgnore // To stop circular loading
	@NotNull
	private Customer customer;	

	public Order() {
		super();
	}

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