package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.VisitDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Visit;
import com.itheima.service.VisitService;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao visitDao;
	
	@Override
	public void save(Visit visit) {
		visitDao.save(visit);
	}

	@Override
	public List<Visit> findByCondition(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return visitDao.findByCondition(detachedCriteria);
	}

	/**
	 * 分页
	 */
	@Override
	public PageBean<Visit> findByCondition(DetachedCriteria detachedCriteria, int pageNumber, int pageSize) {
		//1.创建PageBean pageNumber pageSize startIndex
		PageBean<Visit> pageBean = new PageBean<Visit>(pageNumber , pageSize);
		//4.查询数据库
		//4.1 查询总记录数
		//如果要查询记录数 需要设置投影查询
		detachedCriteria.setProjection(Projections.rowCount());
		int totalRecord = visitDao.findRecord(detachedCriteria);
		
		//4.2 查询分页数据
		detachedCriteria.setProjection(null);
		List<Visit> data = visitDao.findData(detachedCriteria , pageBean.getStartIndex() , pageSize);
		//3.赋值数据
		pageBean.setData(data);
		pageBean.setTotalRecord(totalRecord);
		//2.返回对象
		return pageBean;
	}

	@Override
	public void deleteByVid(Visit visit) {
		visitDao.deleteByVid(visit);
	}

	@Override
	public Visit findByVid(Visit visit) {
		return visitDao.findByVid(visit);
	}

	@Override
	public void update(Visit visit) {
		visitDao.update(visit);
	}

}
