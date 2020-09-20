package com.cma.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cma.domain.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>  {

}
