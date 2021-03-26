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
<!-- 引入datapicker插件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript">
	$(function(){
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#visit_time_start').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#visit_time_end').datepick({dateFormat: 'yy-mm-dd'}); 
	});
	
</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="visitFormId" name="customerForm"
		action="${pageContext.request.contextPath }/visitAction_list"
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
													<td>
														拜访时间
														<INPUT class=textbox id="visit_time_start" readonly="readonly"
														 maxLength=50 name="visitTimeStart" value="${visitTimeStart}">
														-
														<INPUT class=textbox id="visit_time_end" readonly="readonly"
														 maxLength=50 name="visitTimeEnd" value="${visitTimeEnd}">
														<input type="hidden" name="pageNumber" value="1" id="pageNumberId"/>
													</td>
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
													<TD>业务员名称</TD>
													<TD>联系人名称</TD>
													<TD>拜访时间</TD>
													<TD>拜访地方</TD>
													<TD>拜访详情</TD>
													<TD>操作</TD>
												</TR>
												<%-- 遍历拜访数据 --%>
												<c:forEach items="${pageBean.data}" var="tempVisit">
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${tempVisit.customer.custName}</TD>
														<TD>${tempVisit.user.userName}</TD>
														<TD>${tempVisit.linkman.lkmName}</TD>
														<TD>${tempVisit.visitTime}</TD>
														<TD>${tempVisit.visitAddr}</TD>
														<TD>${tempVisit.visitDetail}</TD>
														<TD>
														<a href="${pageContext.request.contextPath }/visitAction_editUI?visitId=${tempVisit.visitId}">修改</a>
														&nbsp;&nbsp;
														<a href="javascript:void(0)" onclick="deleteByVid('${tempVisit.visitId}')">删除</a>
														</TD>
													</TR>
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<script>
									function goToPage(goPage){
										//不能小于1
										if(goPage < 1 ){
											alert("当前页已经是第一页 ");
											return ; 
										}
										//不能大于总页数
										var totalPage = '${pageBean.totalPage}';
										if(goPage > totalPage){
											alert("当前页已经是最后一页");
											return ; 
										}
										//如果能执行到该代码 表示当前页在数据库中能找到 提交表单即可
										//但注意  表单中必须要有当前页的数据 否则 可能只有条件查询
										//先赋值表单的当前页  然后提交表单即可
										$("#pageNumberId").val(goPage);
										$("#visitFormId").submit();
										
									}
									function deleteByVid(visitId){
										var flag = confirm("是否确定删除数据?");
										if(flag){
											location.href="${pageContext.request.contextPath}/visitAction_deleteByVid?visitId="+visitId; 
										}
									}
								</script>
								<TR>
									<TD><SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${pageBean.totalPage}</B>]条记录,[<B>${pageBean.pageNumber}</B>]页
												
												[<A href="javascript:void(0)" onclick="goToPage(${pageBean.pageNumber-1})">前一页</A>]
												<B>
													<%-- 前五后四动态条 前四后五动态条 其目的主要是保证数据一直在中间 不发生变化 --%>
													<c:forEach begin="${pageBean.start}" end="${pageBean.end}" var="num">
														<a href="javascript:void(0)" onclick="goToPage(${num})">
															<font ${num==pageBean.pageNumber ? "color='red'" : ""}>${num}</font>
														</a>
													</c:forEach>
												</B>
												[<A href="javascript:void(0)" onclick="goToPage(${pageBean.pageNumber+1})">后一页</A>] 
												
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
