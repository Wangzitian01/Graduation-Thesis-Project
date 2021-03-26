package com.itheima.service;

import java.util.List;

import com.itheima.domain.Customer;
import com.itheima.domain.User;

public interface UserService {

	void save(User user);

	int findByCode(String userCode);

	User findByNameAndPwd(String userCode, String userPassword);

	void update(User sessionUser);

	List<Customer> findAll();

}
