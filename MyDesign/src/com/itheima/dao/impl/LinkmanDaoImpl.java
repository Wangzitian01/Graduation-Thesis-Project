package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itheima.dao.LinkmanDao;
import com.itheima.domain.Linkman;

@Repository
public class LinkmanDaoImpl implements LinkmanDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Linkman linkman) {
		hibernateTemplate.save(linkman);
	}

	@Override
	public List<Linkman> findByCondition(DetachedCriteria detachedCriteria) {
		return (List<Linkman>) hibernateTemplate.findByCriteria(detachedCriteria);
	}

	@Override
	public void delete(Linkman linkman) {
		hibernateTemplate.delete(linkman);
	}

	@Override
	public Linkman findByLkmId(Linkman linkman) {
		return hibernateTemplate.get(Linkman.class, linkman.getLkmId());
	}

	@Override
	public void update(Linkman linkman) {
		hibernateTemplate.update(linkman);
	}

	/**
	 * 查询分页数据
	 * 返回List<Linkman>
	 * select * from Linkman  where 条件
	 */
	@Override
	public List<Linkman> findData(DetachedCriteria detachedCriteria, int startIndex, int pageSize) {
		return (List<Linkman>) hibernateTemplate.findByCriteria(detachedCriteria, startIndex, pageSize);
	}

	/**
	 * 查询总记录数
	 * 返回一个结果
	 * select count(*) from Linkman where 条件
	 */
	@Override
	public int findRecord(DetachedCriteria detachedCriteria) {
		List<Object> findByCriteria = (List<Object>) hibernateTemplate.findByCriteria(detachedCriteria);
		if(findByCriteria!=null && findByCriteria.size()>0){
			return Integer.valueOf(findByCriteria.get(0).toString());
		}
		return 0;
	}

	@Override
	public List<Linkman> findByCustId(Long custId) {
		return (List<Linkman>) hibernateTemplate.find(" from Linkman where customer.custId = ? ", custId);
	}


}
