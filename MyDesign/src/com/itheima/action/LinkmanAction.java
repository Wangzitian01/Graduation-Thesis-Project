package com.itheima.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.Customer;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.service.LinkmanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import sun.awt.image.ImageWatched.Link;

@Controller
@Scope("prototype")
@ParentPackage("myCrm")
@Namespace("/")
@Results({
	@Result(location="/jsp/linkman/add.jsp" , name="addUI"),
	@Result(location="/jsp/linkman/list.jsp" , name="list"),
	@Result(location="/linkmanAction_findByCondition" , type="redirect", name="findAll"),
	@Result(location="/jsp/linkman/edit.jsp" , name="editUI"),
})
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
	@Autowired
	private CustomerService customerService;
	
	//准备一个LinkmanService
	@Autowired
	private LinkmanService linkmanService;
	
	//属性驱动返回 客户集合数据
	private List<Customer> customerList; 
	/**
	 * 添加前 显示 客户列表数据
	 * @return
	 */
	@Action("linkmanAction_addUI")
	public String addUI(){
		//查询客户列表数据 放入值栈
		customerList = customerService.findAll();
		return "addUI";//跳转到查询页面
	}
	
	/**
	 * 联系人添加方法
	 * @return
	 */
	@Action("linkmanAction_add")
	public String add(){
		linkmanService.save(linkman);
		//return NONE;//跳转到查询所有的页面上
		return "findAll";
	}
	
	
	//使用属性驱动接收pageNumber即可
	private int pageNumber = 1;
	/**
	 * 条件查询
	 * @return
	 */
	@Action("linkmanAction_findByCondition")
	public String findByCondition(){
		//int pageNumber = 1 ;
		int pageSize = 2 ;
		
		
		//1.需要查询 所属客户信息 放到页面上
		//查询客户列表数据 放入值栈
		customerList = customerService.findAll();
		
		//3.拼接条件
		//如果要使用拼接条件 使用 DetachedCriteria
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Linkman.class);
		
		//3.1判断 名称是否存在
		if(!StringUtils.isBlank(linkman.getLkmName())){
			detachedCriteria.add(Restrictions.like("lkmName", "%"+linkman.getLkmName()+"%"));
		}
		
		//3.2判断 性别是否存在
		if(!StringUtils.isBlank(linkman.getLkmGender())){
			detachedCriteria.add(Restrictions.eq("lkmGender", linkman.getLkmGender()));
		}
		//3.2判断 所属客户是否存在  Long类型 需要特殊判断
		//3.2.1 需要先判断对象是否为null
		System.out.println(linkman.getCustomer());
		//System.out.println(linkman.getCustomer().getCustId());
		if(linkman.getCustomer()!=null && linkman.getCustomer().getCustId()!=null){
			detachedCriteria.add(Restrictions.eq("customer.custId", linkman.getCustomer().getCustId()));
		}
		
		//2.查询联系人的数据
		//List<Linkman> linkmanList = linkmanService.findByCondition(detachedCriteria);//from Linkman
		
		PageBean<Linkman> pageBean = linkmanService.findByCondition(detachedCriteria , pageNumber ,pageSize);
		
		//手动放入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "list";
	}
	
	/**
	 * 根据lkmId删除数据
	 * @return
	 */
	@Action("linkmanAction_deleteByLkmId")
	public String delete(){
		linkmanService.delete(linkman);
		return "findAll";
	}
	
	/**
	 * 根据lkmId查询出对应的联系人信息
	 * @return
	 */
	@Action("linkmanAction_editUI")
	public String editUI(){
		//1.查询客户的信息
		//查询客户列表数据 放入值栈  属性驱动已经将数据放入了值栈
		customerList = customerService.findAll();
		
		//2.查询联系人的信息
		Linkman tempLinkman = linkmanService.findByLkmId(linkman);
		//将联系人信息放入值栈 手动放入
		ActionContext.getContext().getValueStack().set("tempLinkman", tempLinkman);
		
		
		return "editUI";
	}
	
	
	/**
	 * 根据id修改联系人数据
	 * @return
	 */
	@Action("linkmanAction_edit")
	public String edit(){
		linkmanService.update(linkman);
		return "findAll";
	}
	
	
	//模型驱动
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		return linkman;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


}
