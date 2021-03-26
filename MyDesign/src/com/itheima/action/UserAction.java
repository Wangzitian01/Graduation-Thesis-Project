package com.itheima.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller//配置控制层
@Scope("prototype")  //原型
@ParentPackage("myCrm")
@Namespace("/")
@Results(value = { 
	@Result(location = "/jsp/success.jsp" , type = "redirect" ,name="success"),
	@Result(location = "/login.jsp" ,name="loginError"), //不可以使用重定向  如果使用 数据丢失
	@Result(location = "/index.jsp" , type = "redirect" ,name="index"),
	@Result(location = "/login.jsp" , type = "redirect" ,name="login"),
})
public class UserAction extends ActionSupport implements ModelDriven<User> {

	@Autowired
	private UserService userService;//不需要实例化 直接通过spring容器注入即可
	/**
	 * 保存 用户的方法
	 * @return
	 */
	@Action("userAction_save")
	public String save(){
		//接收数据 --模型驱动接收数据
		//处理数据
		userService.save(user);
		//响应数据
		return SUCCESS;
	}
	
	
	/**
	 * 校验用户账号是否存在
	 * @return
	 * @throws IOException 
	 */
	@Action("userAction_checkCode")
	public String checkCode() throws IOException{
		//模型驱动获得数据
		//调用service查询数据库数据是否已经存在 响应 0 或者 1即可  0 表示 成功 没有找到数据  1 表示数据库已经存在着记录
		int count = userService.findByCode(user.getUserCode());
		
		//将最后的查询结果返回给浏览器  浏览器接收到 0 或者1 可以进行判断
		ServletActionContext.getResponse().getWriter().print(count);
		
		return NONE;
	}
	
	/**
	 * 登陆
	 * @return
	 */
	@Action("userAction_login")
	public String login(){
		//用户提交的数据--在模型驱动中 用户名和密码
		//调用service处理
		User loginUser = userService.findByNameAndPwd(user.getUserCode() , user.getUserPassword());
		//loginUser 不一定有数据
		if(loginUser != null){
			//表示登陆成功 将数据放入session ==>> 根据request对象来 ==>> request根据ServletActionCOntext来
			ServletActionContext.getRequest().getSession().setAttribute("user", loginUser);
			return "index";// 跳转到首页
		}
		
		//表示登陆失败 --提示用户  登陆失败的信息  使用请求转发携带数据
		//使用值栈存储数据 可以将信息存到值栈中  ==>> 此处不使用值栈 因为每一个Action中都有父类
		//Struts在父类已经封装好方法 可以 返回错误信息数据
		this.addFieldError("msg", "用户名或者密码错误");//页面根据msg 获得值栈中存储的信息
		return "loginError";//回到登陆页面
	}
	/**
	 * 登出
	 * @return
	 */
	@Action("userAction_loginout")
	public String loginout(){
		//获得session 将session中用户数据删除
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		//ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	
	@Action("userAction_edit")
	public String edit(){
		//需不需要传入参数? 不需要   如果要传入参数 传入用户名和密码
		//session中有用户名和密码 只需要获得session中用户名密码  对象 进行修改即可
		User sessionUser = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//细节1 : 命名时 建议不要命名成一样的  有优先级问题
		//细节2 : 只需要传入用户的新密码即可 其他的数据 在session当中可以获得
		sessionUser.setUserPassword(user.getUserPassword());//模型驱动中封装着 用户密码
		userService.update(sessionUser);
		//如果修改成功了 需要让用户重新登陆  并且使用新的密码  老的用户数据没用了  让其失效
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	
	//模型驱动  用来接收数据 或者 响应数据
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
}
