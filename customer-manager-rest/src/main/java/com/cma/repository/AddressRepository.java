package com.cma.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cma.entity.Address;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>{

}
