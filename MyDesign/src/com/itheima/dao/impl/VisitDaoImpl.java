package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.VisitDao;
import com.itheima.domain.Visit;

@Repository
public class VisitDaoImpl implements VisitDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public void save(Visit visit) {
		hibernateTemplate.save(visit);
	}
	@Override
	public List<Visit> findByCondition(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return (List<Visit>) hibernateTemplate.findByCriteria(detachedCriteria);
	}
	/**
	 * 查询总记录数
	 */
	@Override
	public int findRecord(DetachedCriteria detachedCriteria) {
		List<Object> findByCriteria = (List<Object>) hibernateTemplate.findByCriteria(detachedCriteria);
		if(findByCriteria!=null && findByCriteria.size()>0){
			return Integer.valueOf(findByCriteria.get(0).toString());
		}
		return 0;
	}
	/**
	 * 查询分页的数据
	 */
	@Override
	public List<Visit> findData(DetachedCriteria detachedCriteria, int startIndex, int pageSize) {
		return (List<Visit>) hibernateTemplate.findByCriteria(detachedCriteria, startIndex, pageSize);
	}
	@Override
	public void deleteByVid(Visit visit) {
		hibernateTemplate.delete(visit);
	}
	@Override
	public Visit findByVid(Visit visit) {
		return hibernateTemplate.get(Visit.class, visit.getVisitId());
	}
	@Override
	public void update(Visit visit) {
		hibernateTemplate.update(visit);
	}

}
