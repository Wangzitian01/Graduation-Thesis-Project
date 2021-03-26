<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>修改客户拜访信息</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>

<!-- 引入datapicker插件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript">
	$(function(){
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#visit_time').datepick({dateFormat: 'yy-mm-dd'}); 
	});
	
	$(function(){
		//选中客户名称下拉框 和 业务员下拉框 id="userNameId"  id="custNameId"
		$("#custNameId option[value='${tempVisit.customer.custId}']").prop("selected",true);
		$("#userNameId option[value='${tempVisit.user.userId}']").prop("selected",true);
		//在页面加载以后 手动调用jquery代码 加载联系人数据
		//此处changeCustomer($("#custNameId"))   使用jqeury对象
		//jquery对象 和 dom对象的api不可以通用
		changeCustomer(document.getElementById("custNameId"))//使用dom对象
		//手动给联系人赋值后 需要再让联系人的列表中选中
		//换成在ajax中直接添加selected
		//alert('${tempVisit.linkman.lkmId}');
		//$("#lkmNameId option[value='${tempVisit.linkman.lkmId}']").prop("selected",true);
	})
	function changeCustomer(customerEle){
		//customerEle 表示select对象  能获得下拉框中被选中的客户id
		//发送ajax请求
		//url params fn type
		$("#lkmNameId").html("");
		$.post("${pageContext.request.contextPath}/visitAction_changeCustomer",{"custId":customerEle.value},function(data){
			//遍历数据添加到联系人的下拉框中
			//data 就是响应回来的linkman对象集合 需要遍历
			$(data).each(function(){
				//函数中每一个this 都表示一个Linkman对象 需要将linkman的名称 添加到下拉框中
				//获得下拉框 动态往里添加数据
				//每次点击都添加 需要在添加前 将下拉框重置
				if(this.lkmId == '${tempVisit.linkman.lkmId}'){
					$("#lkmNameId").append("<option value='"+this.lkmId+"' selected>"+this.lkmName+"</option>")
				}else{
					$("#lkmNameId").append("<option value='"+this.lkmId+"'>"+this.lkmName+"</option>")
				}
			});
		},"json");
	}
</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/visistAction_edit"
		method=post>
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
									<select id="custNameId" name="customer.custId" onchange="changeCustomer(this)" style="WIDTH: 182px;height:20px">
										<option value="0">请选择客户</option>
										<%-- 遍历客户数据 --%>
										<c:forEach items="${customerList}" var="tempCustomer">
											<option value="${tempCustomer.custId}">${tempCustomer.custName}</option>
										</c:forEach>
									</select>
								</td>
								<td>联系人名称：</td>
								<td>
									<select  name="linkman.lkmId" id="lkmNameId" style="WIDTH: 182px;height:20px">
									</select>
									
									<!-- 隐藏域传输主键 -->
									<input type="hidden" name="visitId" value="${tempVisit.visitId}"/>
								</td>
								<td>业务员 ：</td>
								<td>
									<select id="userNameId" name="user.userId" style="WIDTH: 182px;height:20px">
										<%-- 遍历用户数据  如果 当前临时遍历的id 等于 已经登陆用户的id  默认选中  前提是 用户必须是登陆状态--%>
										<c:forEach items="${userList}" var="tempUser">
											<option value="${tempUser.userId}" ${tempUser.userId == user.userId ? "selected" :""}>${tempUser.userName}</option>
										</c:forEach>
									</select>
								</td>
							</TR>
							
							<TR>
								
								<td>拜访时间：</td>
								<td>
								<INPUT class=textbox id="visit_time" readonly="readonly"
														style="WIDTH: 180px" maxLength=50 name="visitTime" value="${tempVisit.visitTime}">
								</td>
								<td>拜访地点 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="visitAddr" value="${tempVisit.visitAddr}">
								</td>
							</TR>
							
							<TR>
								
								
								<td>拜访详情 ：</td>
								<td>
									<textarea rows="5" cols="25" name="visitDetail"  >${tempVisit.visitDetail}</textarea>
								</td>
								<td></td>
								<td></td>
							</TR>
							
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
