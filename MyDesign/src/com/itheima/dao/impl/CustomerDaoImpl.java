package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	//hibernate模版
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Customer customer) {
		hibernateTemplate.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find(" from Customer" );
	}

	@Override
	public void deleteByCustId(Customer customer) {
		hibernateTemplate.delete(customer);
	}

	@Override
	public Customer findByCustId(Customer customer) {
		return hibernateTemplate.get(Customer.class, customer.getCustId());
	}

	@Override
	public void update(Customer customer) {
		hibernateTemplate.update(customer);
	}

	@Override
	public List<Customer> findByCondition(DetachedCriteria detachedCriteria) {
		//会自动转换在线对象进行查询
		return (List<Customer>) hibernateTemplate.findByCriteria(detachedCriteria);
	} 


}
