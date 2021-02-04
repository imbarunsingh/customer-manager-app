package com.cma.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "PHONE_NUMBER"), @UniqueConstraint(columnNames = "EMAIL_ID") })
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "All details about the Customer")
public class Customer extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 7168329001729485086L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@ApiModelProperty(hidden = true)
	private Long id;

	@Column(name = "FIRST_NAME")
	@ApiModelProperty(notes = "First Name", example = "Joe", required = true)
	@NotBlank
	private String firstName;

	@Column(name = "LAST_NAME")
	@ApiModelProperty(notes = "Last Name", example = "Doe", required = true)
	@NotBlank
	private String lastName;

	@Column(name = "GENDER", length = 10)
	@ApiModelProperty(notes = "Gender", example = "Male", required = true)
	@NotBlank
	private String gender;

	@Column(name = "PHONE_NUMBER", unique = true, length = 15)
	@ApiModelProperty(notes = "Contact Number", example = "911211212", required = true)
	@NotNull
	private Long phoneNumber;

	@Column(name = "EMAIL_ID", unique = true, length = 50)
	@ApiModelProperty(notes = "Email ID", example = "vksingh55@gmail.com", required = true)
	@NotBlank
	private String emailId;

	@OneToMany(mappedBy = "customer", targetEntity = Address.class, cascade = {
			CascadeType.ALL }, fetch = FetchType.EAGER)
	@ApiModelProperty(notes = "Customer Address Details")
	private List<Address> addresses;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ApiModelProperty(notes = "Customer Order Details", hidden = true)
	private Set<Order> orders;

	public Customer() {
	}

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

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}	

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", emailId=" + emailId + ", addresses=" + addresses + ", orders="
				+ orders + "]";
	}

}