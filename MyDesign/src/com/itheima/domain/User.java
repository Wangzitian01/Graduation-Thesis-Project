package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_user")   //tbl  == >> table    sys==>> System
public class User {
	//实体中采用驼峰式命名规则  
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="user_code")
	private String userCode;//'用户账号',
	
	@Column(name="user_name")
	private String userName;//'用户名称',
	
	@Column(name="user_password")
	private String userPassword;//'用户密码',
	
	@Column(name="user_state")
	private String userState;//用户状态

	
	//描述与拜访表之间的关系 一对多
	@OneToMany(targetEntity=Visit.class , mappedBy="user")
	private List<Visit> visits = new ArrayList<>();
	
	
	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	
}
