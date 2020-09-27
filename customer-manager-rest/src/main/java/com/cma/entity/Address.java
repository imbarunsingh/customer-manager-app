package com.cma.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ADDRESS", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
@ApiModel(description = "All details about the customer address")
@EntityListeners(AuditingEntityListener.class)
public class Address extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = -6942359817383205488L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@ApiModelProperty(hidden = true)
	private Long id;

	@Column(name = "STREET_ADDRESS_LINE_1", nullable = false, length = 500)
	@ApiModelProperty(notes = "Street Address - Line 1", example = "4th Main, 12th Cross", required = true)
	private String streetAddressLine1;

	@Column(name = "STREET_ADDRESS_LINE_2", nullable = true, length = 500)
	@ApiModelProperty(notes = "Street Address - Line 2", example = "Prestige Apartment, Whitefield", required = true)
	private String streetAddressLine2;

	@Column(name = "CITY", nullable = false, length = 150)
	@ApiModelProperty(notes = "City", example = "Bangalore", required = true)
	private String city;

	@Column(name = "STATE", nullable = false, length = 150)
	@ApiModelProperty(notes = "City", example = "Bangalore", required = true)
	private String state;

	@Column(name = "COUNTRY", nullable = false, length = 20)
	@ApiModelProperty(notes = "Country", example = "India", required = true)
	private String country;

	@Column(name = "PIN_CODE", nullable = false, length = 20)
	@ApiModelProperty(notes = "PIN Code", example = "560037", required = true)
	private Integer pinCode;

	@Column(name = "LANDMARK", nullable = false, length = 255)
	@ApiModelProperty(notes = "Landmark", example = "Airtel Office", required = true)
	private String landmark;

	@Column(name = "LATITUDE", nullable = true, length = 20)
	@ApiModelProperty(notes = "Latitude", example = "98.12312", required = true)
	private Double latitude;

	@Column(name = "LONGITUDE", nullable = true, length = 20)
	@ApiModelProperty(notes = "Longitude", example = "128.12312", required = true)
	private Double longitude;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
	@ApiModelProperty(hidden = true)
	@JsonIgnore // To stop circular loading
	private Customer customer;

	public Address() {
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
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