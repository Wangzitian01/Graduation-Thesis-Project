package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Visit;

public interface VisitDao {

	void save(Visit visit);

	List<Visit> findByCondition(DetachedCriteria detachedCriteria);

	int findRecord(DetachedCriteria detachedCriteria);

	List<Visit> findData(DetachedCriteria detachedCriteria, int startIndex, int pageSize);

	void deleteByVid(Visit visit);

	Visit findByVid(Visit visit);

	void update(Visit visit);

}
