package com.itheima.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.UserDao;
import com.itheima.domain.Customer;
import com.itheima.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(User user) {
		//使用hibernate保存数据  spring对hibernate进行封装了 
		//使用hibernate模版进行操作
		hibernateTemplate.save(user);
	}

	@Override
	public int findByCode(String userCode) {
		//根据模版进行条件查询  hibernate的模版底层基于 hibernate 使用的是 HQL语句
		List<Object> list = (List<Object>) hibernateTemplate.find(" from User where userCode = ? ", userCode);
		if(list != null && list.size() > 0 ){  // 如果没有查询到 就是表示 可以注册时  list为null  抛出空指针异常
			return 1;
		}
		
		return 0;
	}

	@Override
	public User findByNameAndPwd(String userCode, String userPassword) {
		List<User> userList= (List<User>) hibernateTemplate.find(" from User where userCode = ? and userPassword = ? ", userCode , userPassword);
		
		if(userList!= null && userList.size()>0){
			return userList.get(0);//表示list有用户数据
		}
		return null;
	}

	@Override
	public void update(User sessionUser) {
		hibernateTemplate.update(sessionUser);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find("from User ");
	}

}
