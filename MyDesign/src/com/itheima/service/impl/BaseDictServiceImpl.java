package com.itheima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.BaseDictDao;
import com.itheima.domain.BaseDict;
import com.itheima.service.BaseDictService;

@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictDao baseDictDao;
	@Override
	public List<BaseDict> findBaseDictByTypeCode(String typeCode) {
		//调用dao查询数据即可
		return baseDictDao.findBaseDictByTypeCode(typeCode);
	}

}
