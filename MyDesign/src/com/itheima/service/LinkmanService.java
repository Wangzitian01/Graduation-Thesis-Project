package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;

public interface LinkmanService {

	void save(Linkman linkman);

	//List<Linkman> findByCondition(DetachedCriteria detachedCriteria);

	void delete(Linkman linkman);

	Linkman findByLkmId(Linkman linkman);

	void update(Linkman linkman);

	//PageBean<Linkman> findByCondition(DetachedCriteria detachedCriteria);

	PageBean<Linkman> findByCondition(DetachedCriteria detachedCriteria, int pageNumber, int pageSize);

	List<Linkman> findByCustId(Long custId);

}
