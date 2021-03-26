package com.itheima.service;

import java.util.List;

import com.itheima.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> findBaseDictByTypeCode(String typeCode);

}
