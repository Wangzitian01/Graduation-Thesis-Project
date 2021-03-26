package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Linkman;

public interface LinkmanDao {

	void save(Linkman linkman);

	List<Linkman> findByCondition(DetachedCriteria detachedCriteria);

	void delete(Linkman linkman);

	Linkman findByLkmId(Linkman linkman);

	void update(Linkman linkman);

	List<Linkman> findData(DetachedCriteria detachedCriteria, int startIndex, int pageSize);

	int findRecord(DetachedCriteria detachedCriteria);

	List<Linkman> findByCustId(Long custId);

}
