package com.itheima.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
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
import com.itheima.domain.Visit;
import com.itheima.service.CustomerService;
import com.itheima.service.LinkmanService;
import com.itheima.service.UserService;
import com.itheima.service.VisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("myCrm")
@Results({
	@Result(location= "/jsp/visit/add.jsp" , name="addUI"),
	@Result(location= "/jsp/visit/list.jsp" , name="list"),
	@Result(location= "/visitAction_list" ,type="redirect", name="findAll"),
	@Result(location= "/jsp/visit/edit.jsp" , name="editUI"),
})
public class VisitAction extends ActionSupport implements ModelDriven<Visit> {

	//客户的service
	@Autowired
	private CustomerService customerService;
	//用户的service
	@Autowired
	private UserService userService;
	//联系人的service
	@Autowired
	private LinkmanService linkmanService; 
	
	@Autowired
	private VisitService visitService;
	/**
	 * 添加客户拜访前查询客户和用户列表数据
	 * @return
	 */
	@Action("visitAction_addUI")
	public String addUI(){
		//1.查询客户列表数据 准备客户的service
		List<Customer> customerList = customerService.findAll();
		//2.查询用户列表数据 准备用户的service
		List<Customer> userList = userService.findAll();
		
		//将数据放入值栈 转发给 add.jsp
		ActionContext.getContext().getValueStack().set("customerList", customerList);
		ActionContext.getContext().getValueStack().set("userList", userList);
		return "addUI";
	}
	
	
	private Long custId;
	/**
	 * 修改客户时 需要动态查询出联系人数据 响应json回去给页面
	 * @return
	 * @throws IOException 
	 */
	@Action("visitAction_changeCustomer")
	public String changeCustomer() throws IOException{
		//需要联系人的service
		List<Linkman> linkmans =  linkmanService.findByCustId(custId);
		
		//响应json  需要有json 的包
		//使用JSONArray
		/*
		 * 如果是以下代码 报错
		 * String json = JSONArray.fromObject(linkmans).toString(); 
		 * 没有session的问题
		 * 因为 在查询Linkman 的同时 并没有马上查询Customer  事务在service层响应回来结束
		 * 最后在Action中进行转换的时候  此时hibernate 会再去查询数据库 得到Customer 但是此时的session已经关闭了
		 * 建议  : 在转换json字符串同时 将不需要的数据排除掉
		 * 可以通过JsonConfig进行设置
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"customer" , "visits"});
		String json = JSONArray.fromObject(linkmans, jsonConfig).toString();
		
		System.out.println(json);
		
		//需要将json响应给浏览器  需要有response对象 
		//响应回去会出现乱码问题 原因 是 之前没有处理乱码 但struts 通过模型驱动 属性驱动已经处理好了乱码
		//但此时 使用原生的response对象 需要手动处理乱码
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE; //响应json 不需要跳转页面
	}
	
	/**
	 * 客户拜访的添加
	 * @return
	 */
	@Action("visistAction_add")
	public String add(){
		visitService.save(visit);
		return "findAll";
	}
	
	//接收用户输入的条件
	private String visitTimeStart;
	private String visitTimeEnd;
	
	//成员变量接收浏览器传入的当前页
	private int pageNumber = 1 ; 
	/**
	 * 条件查询和分页的Action
	 * @return
	 */
	@Action("visitAction_list")
	public String list(){
		//定义pageSize
		int pageSize = 2; 
		
		
		//如果有条件需要离线对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Visit.class);
		
		//条件查询的代码
		//判断数据是否为null 如果不为null 拼接条件  开始时间    数据的时间  结束时间
		if(!StringUtils.isBlank(visitTimeStart)){
			//查询的时间 大于等于开始时间
			detachedCriteria.add(Restrictions.ge("visitTime", visitTimeStart));
		}
		if(!StringUtils.isBlank(visitTimeEnd)){
			//查询的时间 小于等于结束时间
			detachedCriteria.add(Restrictions.le("visitTime", visitTimeEnd));
		}
		
		//查询List数据
		//List<Visit> visitsList = visitService.findByCondition(detachedCriteria);
		
		//分页需要当前页 和 每页显示个数
		PageBean<Visit> pageBean = visitService.findByCondition(detachedCriteria , pageNumber , pageSize );
		
		//将数据传给值栈
		ActionContext.getContext().getValueStack().set("pageBean" , pageBean);
		return "list";
	}
	
	/**
	 * 根据Id删除拜访信息
	 * @return
	 */
	@Action("visitAction_deleteByVid")
	public String deleteByVid(){
		visitService.deleteByVid(visit);
		return "findAll";
	}
	
	/**
	 * 修改前展示页面的数据
	 * @return
	 */
	@Action("visitAction_editUI")
	public String editUI(){
		//1.根据ID查询出 拜访记录的信息
		Visit tempVisit = visitService.findByVid(visit);
		
		
		//2. 查询静态数据  客户列表 和 用户列表
		//2.1.查询客户列表数据 
		List<Customer> customerList = customerService.findAll();
		//2.2 .查询用户列表数据 
		List<Customer> userList = userService.findAll();
		
		//将数据放入值栈 转发给 add.jsp
		ActionContext.getContext().getValueStack().set("customerList", customerList);
		ActionContext.getContext().getValueStack().set("userList", userList);
		ActionContext.getContext().getValueStack().set("tempVisit", tempVisit);
		return "editUI";
	}
	
	/**
	 * 修改拜访记录
	 * @return
	 */
	@Action("visistAction_edit")
	public String edit(){
		visitService.update(visit);
		return "findAll";
	}
	
	
	private Visit visit = new Visit();
	@Override
	public Visit getModel() {
		return visit;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getVisitTimeStart() {
		return visitTimeStart;
	}

	public void setVisitTimeStart(String visitTimeStart) {
		this.visitTimeStart = visitTimeStart;
	}

	public String getVisitTimeEnd() {
		return visitTimeEnd;
	}

	public void setVisitTimeEnd(String visitTimeEnd) {
		this.visitTimeEnd = visitTimeEnd;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	
	
	
}
