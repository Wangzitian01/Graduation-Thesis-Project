package com.itheima.interceptor;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AuthInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//用于判断权限 判断session中有没有user对象 有放行 没有返回登陆页面
		//获得Session 就需要获得request对象
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user!= null){
			//已经登陆的用户 不进行拦截 放行
			return invocation.invoke();
		}
		
		//表示没有登陆成功 跳转到登陆页面
		return "loginJsp";
	}

}
