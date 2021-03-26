<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<script>
	$(function(){
		//跟刚才不一样的是 此处的数据封装在模型驱动中 刚才的数据封装在了一个临时对象中
		$("#levelId option[value='${baseDictLevel.dictId}']").prop("selected",true);
		$("#sourceId option[value='${baseDictSource.dictId}']").prop("selected",true);
		$("#industryId option[value='${baseDictIndustry.dictId}']").prop("selected",true);
	})
</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customerAction_findByCondition"
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
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" maxLength=50 name="custName" value="${custName}"></TD>
													
													<TD>客户级别：</TD>
													<TD>
														<select id="levelId" name="baseDictLevel.dictId" style="WIDTH: 180px">
															<option value="">-请选择-</option>
															<!-- 此处从Action获得集合数据遍历即可 -->
															<c:forEach items="${baseDictLevelList}" var="baseDictLevel">
																<option value="${baseDictLevel.dictId}">${baseDictLevel.dictItemName}</option>
															</c:forEach>
														</select>
													</TD>
														
													<TD>客户来源：</TD>
													<TD>
														<select id="sourceId" name="baseDictSource.dictId" style="WIDTH: 180px">
															<option value="">-请选择-</option>
															<c:forEach items="${baseDictSourceList}" var="baseDictSource">
																<option value="${baseDictSource.dictId}">${baseDictSource.dictItemName}</option>
															</c:forEach>
														</select>
													</TD>
														
													<TD>客户所属行业：</TD>
													<TD>
														<select id="industryId" name="baseDictIndustry.dictId" style="WIDTH: 180px">
															<option value="">-请选择-</option>
															<c:forEach items="${baseDictIndustryList}" var="baseDictIndustry">
																<option value="${baseDictIndustry.dictId}">${baseDictIndustry.dictItemName}</option>
															</c:forEach>
														</select>
													</TD>
													
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>客户所属行业</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<!-- 遍历客户列表数据 -->
												<c:forEach items="${customerList}" var="tempCustomer">
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${tempCustomer.custName}</TD>
														<TD>${tempCustomer.baseDictLevel.dictItemName}</TD>
														<TD>${tempCustomer.baseDictSource.dictItemName}</TD>
														<TD>${tempCustomer.baseDictIndustry.dictItemName}</TD>
														<TD>${tempCustomer.custPhone}</TD>
														<TD>${tempCustomer.custMobile}</TD>
														<TD>
														<a href="${pageContext.request.contextPath }/customer_editUI?custId=${tempCustomer.custId}">修改</a>
														&nbsp;&nbsp;
														<a href="javascript:void(0)" onclick="deleteByCid('${tempCustomer.custId}')">删除</a>
														</TD>
													</TR>
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<!-- 先放在此处的脚本代码 -->
								<script>
									function deleteByCid(custId){
										//alert(custId);
										//根据id删除数据 ${tempCustomer.custId} 这个表达式 只在循环内部生效
										//此处由于每一个删除的链接都有自己的独特的id 所有需要传参数
										//建议 传参的时候 加上两个单引号   通用  即支持字符串 和 数字
										var flag = confirm("是否确定删除数据?");
										if(flag){
											//访问服务器删除数据
											location.href="${pageContext.request.contextPath}/customerAction_delete?custId="+custId
										}
									}					
								</script>
								<TR>
									<TD><SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>10</B>]条记录,[<B>1</B>]页
												
												[<A href="#">前一页</A>]
												<B>1</B>
												[<A href="#">后一页</A>] 
												
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
