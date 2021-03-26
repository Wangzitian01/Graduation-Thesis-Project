package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.UserDao;
import com.itheima.domain.Customer;
import com.itheima.domain.User;
import com.itheima.service.UserService;

@Service
@Transactional  //配置默认事务
public class UserServiceImpl  implements UserService{

	//注入一个userDao
	@Autowired
	private UserDao userDao;
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	@Override
	public int findByCode(String userCode) {
		return userDao.findByCode(userCode);
	}
	@Override
	public User findByNameAndPwd(String userCode, String userPassword) {
		return userDao.findByNameAndPwd(userCode ,userPassword );
	}
	@Override
	public void update(User sessionUser) {
		userDao.update(sessionUser);
	}
	@Override
	public List<Customer> findAll() {
		return userDao.findAll();
	}
	
}
