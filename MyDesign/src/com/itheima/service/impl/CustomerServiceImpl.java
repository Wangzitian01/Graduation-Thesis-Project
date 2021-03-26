package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	//准备customerDao
	@Autowired
	private CustomerDao customerDao;
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	@Override
	public void deleteByCustId(Customer customer) {
		customerDao.deleteByCustId(customer);
	}
	@Override
	public Customer findByCustId(Customer customer) {
		return customerDao.findByCustId(customer);
	}
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	@Override
	public List<Customer> findByCondition(DetachedCriteria detachedCriteria) {
		return customerDao.findByCondition(detachedCriteria);
	}


}
