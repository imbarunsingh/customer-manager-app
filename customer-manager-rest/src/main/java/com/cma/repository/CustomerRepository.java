package com.cma.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cma.domain.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	//List<Customer> findAllBySearchText(String filterText, Pageable pageable);
}
