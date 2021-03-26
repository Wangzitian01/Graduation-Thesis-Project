package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.BaseDictDao;
import com.itheima.domain.BaseDict;

@Repository
public class BaseDictDaoImpl implements BaseDictDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		return (List<BaseDict>) hibernateTemplate.find(" from BaseDict where dictTypeCode = ? ", typeCode);
	}

}
