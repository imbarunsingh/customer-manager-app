package com.cma.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ADDRESS", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
@ApiModel(description="All details about the customer address")
public class Address extends AuditTrail {

	private static final long serialVersionUID = -6942359817383205488L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "STREET_ADDRESS_LINE_1", nullable = false, length = 500)
	private String streetAddressLine1;

	@Column(name = "STREET_ADDRESS_LINE_2", nullable = true, length = 500)
	private String streetAddressLine2;

	@Column(name = "CITY", nullable = false, length = 150)
	private String city;

	@Column(name = "STATE", nullable = false, length = 150)
	private String state;

	@Column(name = "COUNTRY", nullable = false, length = 20)
	private Integer country;

	@Column(name = "PIN_CODE", nullable = false, length = 20)
	private Integer pinCode;

	@Column(name = "LANDMARK", nullable = false, length = 255)
	private Integer landmark;

	@Column(name = "LATITUDE", nullable = true, length = 20)
	private Double latitude;

	@Column(name = "LONGITUDE", nullable = true, length = 20)
	private Double longitude;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	@ApiModelProperty(hidden = true)
	private Customer customer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetAddressLine1() {
		return streetAddressLine1;
	}

	public void setStreetAddressLine1(String streetAddressLine1) {
		this.streetAddressLine1 = streetAddressLine1;
	}

	public String getStreetAddressLine2() {
		return streetAddressLine2;
	}

	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}