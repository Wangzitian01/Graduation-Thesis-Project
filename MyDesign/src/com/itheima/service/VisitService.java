package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.PageBean;
import com.itheima.domain.Visit;

public interface VisitService {

	void save(Visit visit);

	List<Visit> findByCondition(DetachedCriteria detachedCriteria);

	PageBean<Visit> findByCondition(DetachedCriteria detachedCriteria, int pageNumber, int pageSize);

	void deleteByVid(Visit visit);

	Visit findByVid(Visit visit);

	void update(Visit visit);

}
