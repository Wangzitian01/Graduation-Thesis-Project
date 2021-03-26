package com.itheima.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="crm_visit")
public class Visit {
/**
  `visit_id` varchar(32) NOT NULL,  主键 
  `visit_cust_id` bigint(32) DEFAULT NULL COMMENT '客户id',
  `visit_user_id` bigint(32) DEFAULT NULL COMMENT '负责人id',
  `visit_time` date DEFAULT NULL COMMENT '拜访时间',
  `visit_interviewee` varchar(32) DEFAULT NULL COMMENT '被拜访人',
  `visit_addr` varchar(128) DEFAULT NULL COMMENT '拜访地点',
  `visit_detail` varchar(256) DEFAULT NULL COMMENT '拜访详情',
  `visit_nexttime` date DEFAULT NULL COMMENT '下次拜访时间',
 */
	@Id
	@Column(name="visit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long visitId;
	
	@Column(name="visit_time")
	private String visitTime;
	
	@Column(name="visit_interviewee")
	private String visitInterviewee;
	
	@Column(name="visit_addr")
	private String visitAddr;
	
	@Column(name="visit_detail")
	private String visitDetail;
	
	@Column(name="visit_nexttime")
	private String visitNexttime;
	
	
	//多对一
	//特殊外键字段   表示拜访的客户是谁
	@ManyToOne(targetEntity=Customer.class)
	@JoinColumn(name="visit_cust_id" , referencedColumnName="cust_id")
	private Customer customer;
	
	//特殊外键字段   表示谁去拜访的客户
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="visit_user_id" , referencedColumnName="user_id")
	private User user;
	
	//特殊外键字段 表示用户去拜访的客户下面的员工是谁
	@ManyToOne(targetEntity=Linkman.class)
	@JoinColumn(name="visit_lkm_id" , referencedColumnName="lkm_id")
	private Linkman linkman;

	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitInterviewee() {
		return visitInterviewee;
	}

	public void setVisitInterviewee(String visitInterviewee) {
		this.visitInterviewee = visitInterviewee;
	}

	public String getVisitAddr() {
		return visitAddr;
	}

	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}

	public String getVisitDetail() {
		return visitDetail;
	}

	public void setVisitDetail(String visitDetail) {
		this.visitDetail = visitDetail;
	}

	public String getVisitNexttime() {
		return visitNexttime;
	}

	public void setVisitNexttime(String visitNexttime) {
		this.visitNexttime = visitNexttime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Linkman getLinkman() {
		return linkman;
	}

	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}
	
	
	
	
	
}
