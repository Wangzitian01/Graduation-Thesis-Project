package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;

public interface CustomerService {

	void save(Customer customer);

	List<Customer> findAll();

	void deleteByCustId(Customer customer);

	Customer findByCustId(Customer customer);

	void update(Customer customer);

	List<Customer> findByCondition(DetachedCriteria detachedCriteria);

}
