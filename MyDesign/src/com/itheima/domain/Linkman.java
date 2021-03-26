package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

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
@Table(name="cst_linkman")
public class Linkman {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lkm_id")
	private Long lkmId;//'联系人编号(主键)',
	@Column(name="lkm_name")
	private String lkmName;//'联系人姓名',
	
	
	@Column(name="lkm_gender")
	private String lkmGender;//'联系人性别',
	@Column(name="lkm_phone")
	private String lkmPhone;//'联系人办公电话',
	@Column(name="lkm_mobile")
	private String lkmMobile;//'联系人手机',
	
	@Column(name="lkm_email")
	private String lkmEmail;// '联系人邮箱',
	@Column(name="lkm_qq")
	private String lkmQq;//'联系人qq',
	@Column(name="lkm_position")
	private String lkmPosition;//'联系人职位',
	
	@Column(name="lkm_memo")
	private String lkmMemo;//'联系人备注',
	
	//描述多对一
	//private String lkm_cust_id;// '客户id', 外键
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="lkm_cust_id" , referencedColumnName="cust_id")
	private Customer customer;

	
	
	//描述与拜访表之间的关系 一对多
	@OneToMany(targetEntity=Visit.class , mappedBy="linkman")
	private List<Visit> visits = new ArrayList<>();
	
	
	
	
	
	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Long getLkmId() {
		return lkmId;
	}

	public void setLkmId(Long lkmId) {
		this.lkmId = lkmId;
	}

	public String getLkmName() {
		return lkmName;
	}

	public void setLkmName(String lkmName) {
		this.lkmName = lkmName;
	}

	public String getLkmGender() {
		return lkmGender;
	}

	public void setLkmGender(String lkmGender) {
		this.lkmGender = lkmGender;
	}

	public String getLkmPhone() {
		return lkmPhone;
	}

	public void setLkmPhone(String lkmPhone) {
		this.lkmPhone = lkmPhone;
	}

	public String getLkmMobile() {
		return lkmMobile;
	}

	public void setLkmMobile(String lkmMobile) {
		this.lkmMobile = lkmMobile;
	}

	public String getLkmEmail() {
		return lkmEmail;
	}

	public void setLkmEmail(String lkmEmail) {
		this.lkmEmail = lkmEmail;
	}

	public String getLkmQq() {
		return lkmQq;
	}

	public void setLkmQq(String lkmQq) {
		this.lkmQq = lkmQq;
	}

	public String getLkmPosition() {
		return lkmPosition;
	}

	public void setLkmPosition(String lkmPosition) {
		this.lkmPosition = lkmPosition;
	}

	public String getLkmMemo() {
		return lkmMemo;
	}

	public void setLkmMemo(String lkmMemo) {
		this.lkmMemo = lkmMemo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
	
	
	
	
	
}
