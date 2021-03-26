package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cst_customer")
public class Customer {
/**
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '客户编号(主键)',
  `cust_name` varchar(32) NOT NULL COMMENT '客户名称(公司名称)',
  `cust_source` varchar(32) DEFAULT NULL COMMENT '客户信息来源',
  `cust_industry` varchar(32) DEFAULT NULL COMMENT '客户所属行业',
  `cust_level` varchar(32) DEFAULT NULL COMMENT '客户级别',
  `cust_phone` varchar(64) DEFAULT NULL COMMENT '固定电话',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '移动电话',
 */
	@Id
	@Column(name="cust_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long custId;
	
	@Column(name="cust_name")
	private String custName;

	@Column(name="cust_phone")
	private String custPhone;
	
	@Column(name="cust_mobile")
	private String custMobile;
	
	
	/**
	 * @JoinColumn(name="数据库中新增列的名称" , referencedColumnName="指向主表的主键" )
	 */
	//特殊字段
	//多对一  baseDictSource  baseDictLevel    baseDictIndustry
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_source" , referencedColumnName="dict_id" )//往数据库表中加入一列
	private BaseDict baseDictSource;//来源
	
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_level" , referencedColumnName="dict_id" )//往数据库表中加入一列
	private BaseDict baseDictLevel;//级别
	
	@ManyToOne(targetEntity=BaseDict.class)
	@JoinColumn(name="cust_industry" , referencedColumnName="dict_id" )//往数据库表中加入一列
	private BaseDict baseDictIndustry;//所属行业

	
	
	//mappedBy="customer" 操作Linkman时 不会操作customer属性
	//集合为什么需要实例化 因为 操作的同时 如果不提前实例化 可能会出现 需要在外部先实例化集合 然后赋值
	//如果已经提前赋值 可以直接获得对象 操作即可
	//描述一对多  需要在一方添加级联删除
	@OneToMany(targetEntity=Linkman.class , mappedBy="customer" , cascade=CascadeType.REMOVE)
	private List<Linkman> linkmans = new ArrayList<>();
	
	
	
	
	
	//描述与拜访表之间的关系 一对多
	@OneToMany(targetEntity=Visit.class , mappedBy="customer")
	private List<Visit> visits = new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public List<Linkman> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List<Linkman> linkmans) {
		this.linkmans = linkmans;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public BaseDict getBaseDictSource() {
		return baseDictSource;
	}

	public void setBaseDictSource(BaseDict baseDictSource) {
		this.baseDictSource = baseDictSource;
	}

	public BaseDict getBaseDictLevel() {
		return baseDictLevel;
	}

	public void setBaseDictLevel(BaseDict baseDictLevel) {
		this.baseDictLevel = baseDictLevel;
	}

	public BaseDict getBaseDictIndustry() {
		return baseDictIndustry;
	}

	public void setBaseDictIndustry(BaseDict baseDictIndustry) {
		this.baseDictIndustry = baseDictIndustry;
	}
	
	
	
	
	
	
	
	
	
	
}
