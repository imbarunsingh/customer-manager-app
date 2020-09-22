package com.cma.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "PHONE_NUMBER"), @UniqueConstraint(columnNames = "EMAIL_ID") })
@ApiModel(description="All details about the Customer")
public class Customer extends AuditTrail {

	private static final long serialVersionUID = 7168329001729485086L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	@ApiModelProperty(notes = "Unique identifier of the customer. No two customer can have the same id.", example = "1")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false, length = 255)
	@ApiModelProperty(notes = "First Name", example = "Joe", required = true)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 255)
	@ApiModelProperty(notes = "Last Name", example = "Doe", required = true)
	private String lastName;

	@Column(name = "GENDER", nullable = false, length = 10)
	@ApiModelProperty(notes = "Gender", example = "Male", required = true)
	private String gender;

	@Column(name = "PHONE_NUMBER", unique = true, nullable = false, length = 15)
	@ApiModelProperty(notes = "Contact Number", example = "91-11211212", required = true)
	private Integer phoneNumber;

	@Column(name = "EMAIL_ID", unique = true, nullable = false, length = 50)
	@ApiModelProperty(notes = "Email ID", example = "vksingh55@gmail.com", required = true)
	private String emailId;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "Customer Address Details")
	private Set<Address> addresses;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)	
	@ApiModelProperty(notes = "Customer Order Details")
	private Set<Order> orders;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}