package com.itheima.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_base_dict")
public class BaseDict {
/**
  `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
  `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
  `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
  `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
  `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
  `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
  `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
  `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',
 */
	@Id
	@Column(name="dict_id")
	private String dictId;//主键
	
	@Column(name="dict_type_code")
	private String dictTypeCode;//类别码  001  002 003 
	
	@Column(name="dict_type_name")
	private String dictTypeName;// 类别的名称  对 类别码的进行描述  就是表名
	
	@Column(name="dict_item_name")
	private String dictItemName;//  就是每个字典自己的名称
	
	@Column(name="dict_item_code")
	private String dictItemCode;
	
	@Column(name="dict_sort")
	private String dictSort;
	
	@Column(name="dict_enable")
	private String dictEnable;
	
	@Column(name="dict_memo")
	private String dictMemo;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictTypeCode() {
		return dictTypeCode;
	}

	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getDictItemName() {
		return dictItemName;
	}

	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}

	public String getDictItemCode() {
		return dictItemCode;
	}

	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}

	public String getDictSort() {
		return dictSort;
	}

	public void setDictSort(String dictSort) {
		this.dictSort = dictSort;
	}

	public String getDictEnable() {
		return dictEnable;
	}

	public void setDictEnable(String dictEnable) {
		this.dictEnable = dictEnable;
	}

	public String getDictMemo() {
		return dictMemo;
	}

	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}
	
	
	
	
}
