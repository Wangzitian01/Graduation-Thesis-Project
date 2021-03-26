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

import com.itheima.domain.BaseDict;
import com.itheima.domain.Customer;
import com.itheima.service.BaseDictService;
import com.itheima.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@ParentPackage("myCrm")
@Namespace("/")
@Results({
	@Result(location = "/jsp/customer/add.jsp" ,name ="addUI"),
	@Result(location = "/jsp/customer/edit.jsp" ,name ="editUI"),
	@Result(location = "/jsp/customer/list.jsp" ,name ="list"),
	@Result(location = "/customerAction_list" , type="redirect",name ="findAll"),
})
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	//准备一个客户表的service
	@Autowired
	private CustomerService customerService;
	//准备一个字典表的service
	@Autowired
	private BaseDictService baseDictService ; 
	
	private List<BaseDict> baseDictLevelList ;
	private List<BaseDict> baseDictIndustryList;
	private List<BaseDict> baseDictSourceList ;
	
	
	
	//属性驱动返回客户列表集合
	private List<Customer> customerList;
	/**
	 * 准备一个addUI的Action
	 */
	@Action("customerAction_addUI")
	public String addUI(){
		//调用service查询 字典表数据
		//查询级别 
		baseDictLevelList =  baseDictService.findBaseDictByTypeCode("006");
		//查询所属行业
		baseDictIndustryList = baseDictService.findBaseDictByTypeCode("001");
		//查询来源
		baseDictSourceList = baseDictService.findBaseDictByTypeCode("009");
		
		//数据将给页面显示  需要将数据放入值栈  手动或者自动(使用struts中的属性驱动)
		//将集合数据 封装成私有的成员变量 同时提供get方法即可 数据会被自动放入值栈
		return "addUI";
	}

	/**
	 * 客户的保存操作
	 * @return
	 */
	@Action("customerAction_save")
	public String save(){
		System.out.println(customer);
		customerService.save(customer);
		return "findAll";//一会修改成 查询所有的返回值
	}
	
	/**
	 * 查询客户列表数据
	 * @return
	 */
	@Action("customerAction_list")
	public String list(){
		//查询客户列表的数据
		customerList = customerService.findAll();
		
		
		//调用service查询 字典表数据  下拉框的共有数据
		//查询级别 
		baseDictLevelList =  baseDictService.findBaseDictByTypeCode("006");
		//查询所属行业
		baseDictIndustryList = baseDictService.findBaseDictByTypeCode("001");
		//查询来源
		baseDictSourceList = baseDictService.findBaseDictByTypeCode("009");
		
		return "list";
	}
	
	/**
	 * 根据id删除客户数据
	 * @return
	 */
	@Action("customerAction_delete")
	public String delete(){
		//先获得客户 再删除客户数据
		//从数据库获得的用户 具有list集合 才可以进行 级联删除
		Customer tempCustomer = customerService.findByCustId(customer);
		customerService.deleteByCustId(tempCustomer);
		return "findAll";//返回
	}
	
	/**
	 * 根据custId 获得客户数据 显示给jsp
	 * @return
	 */
	@Action("customer_editUI")
	public String editUI(){
		//1.根据id获得的客户数据
		Customer tempCustomer = customerService.findByCustId(customer);
		
		
		//2.需要查询下拉框的数据
		//调用service查询 字典表数据  下拉框的共有数据
		//查询级别 
		baseDictLevelList =  baseDictService.findBaseDictByTypeCode("006");
		//查询所属行业
		baseDictIndustryList = baseDictService.findBaseDictByTypeCode("001");
		//查询来源
		baseDictSourceList = baseDictService.findBaseDictByTypeCode("009");
		
		//返回数据 可以使用属性驱动 也可以手动放入值栈
		ActionContext.getContext().getValueStack().set("tempCustomer", tempCustomer);
		
		return "editUI";
	}
	
	/**
	 * 修改客户数据
	 * @return
	 */
	@Action("customerAction_edit")
	public String edit(){
		//数据都被封装到模型驱动中 
		customerService.update(customer);
		return "findAll";
	}
	
	
	/**
	 * 条件查询
	 * @return
	 */
	@Action("customerAction_findByCondition")
	public String findByCondition(){
		//不管条件的部分 回去还是一样的页面  需要准备 静态数据 (下拉框数据)
		//1.需要查询下拉框的数据
		//调用service查询 字典表数据  下拉框的共有数据
		//查询级别 
		baseDictLevelList =  baseDictService.findBaseDictByTypeCode("006");
		//查询所属行业
		baseDictIndustryList = baseDictService.findBaseDictByTypeCode("001");
		//查询来源
		baseDictSourceList = baseDictService.findBaseDictByTypeCode("009");
		
		
		//2.考虑查询sql的部分  hibernate提供了离线对象 可以封装条件
		//DetachedCriteria 离线 游离对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		//判断是否非空 如果不为空  添加条件  数据都被模型驱动封装了  baseDictSource  baseDictLevel    baseDictIndustry
		//isBlank判断字符串是否为null 或者是否为""  如果是 返回true
		//判断名称
		if(!StringUtils.isBlank(customer.getCustName())){
			//不为null 拼接条件
			detachedCriteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		}
		//判断级别
		if(customer.getBaseDictLevel()!=null && !StringUtils.isBlank(customer.getBaseDictLevel().getDictId())){
			//不为null 拼接条件
			detachedCriteria.add(Restrictions.eq("baseDictLevel.dictId",customer.getBaseDictLevel().getDictId() ));
		}
		//判断来源
		if(customer.getBaseDictSource()!=null && !StringUtils.isBlank(customer.getBaseDictSource().getDictId())){
			//不为null 拼接条件
			detachedCriteria.add(Restrictions.eq("baseDictSource.dictId",customer.getBaseDictSource().getDictId() ));
		}
		//判断所属行业
		if(customer.getBaseDictIndustry()!=null && !StringUtils.isBlank(customer.getBaseDictIndustry().getDictId())){
			//不为null 拼接条件
			detachedCriteria.add(Restrictions.eq("baseDictIndustry.dictId",customer.getBaseDictIndustry().getDictId() ));
		}
		
		//表示sql已经拼接完成 只需要传入dao 调用api即可
		//返回值 必须叫做customerList  因为查询所有也是这个名称
		customerList = customerService.findByCondition(detachedCriteria);
		
		
		
		return "list";
	}
	
	
	
	
	
	
	
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	public List<BaseDict> getBaseDictLevelList() {
		return baseDictLevelList;
	}
	public List<BaseDict> getBaseDictIndustryList() {
		return baseDictIndustryList;
	}
	public List<BaseDict> getBaseDictSourceList() {
		return baseDictSourceList;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}


}
