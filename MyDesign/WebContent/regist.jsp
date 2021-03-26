<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<script>
	//发送ajax校验
	function checkUserCode(){
		//发送ajax请求 到服务器校验数据是否存在  需要jquery
		//url params fn  type
		var userCode =  $("#userCode").val();
		$.post("${pageContext.request.contextPath }/userAction_checkCode" , {"userCode":userCode} , function(data){
			if(data=="1"){
				//失败 数据库已经存在记录
				alert("用户名已经存在 请换一个重试"); //弹出框 不会阻止表单提交  建议 使用隐藏域  让表单不提交
				$("#submitFormFlag").val("false");
			}else{
				$("#submitFormFlag").val("true");
			}
		})
	}
	//表单提交事件  主要查看 表单是否允许提交
	function checkCode(){
		//获得到隐藏域 如果是true  允许提交  如果是false 不允许提交
		var flagVal = $("#submitFormFlag").val();
		if(flagVal=="true"){
			//获得表单提交
			return true;
		}else{
			alert("用户名已经存在  请修改");
			return false;
		}
	}
	
</script>


<META content="MSHTML 6.00.6000.16809" name=GENERATOR></HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath}/userAction_save" onsubmit="return checkCode()" method=post>
<!-- 表单的隐藏域  用来判断表单是否可以提交 -->
<input type="hidden" id="submitFormFlag" value="true"> 
<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" border=0></TD>
  </TR>
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                	<INPUT id="userCode"  style="WIDTH: 130px" name="userCode" onblur="checkUserCode()">
                </TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id=txtPwd style="WIDTH: 130px" type=password name="userPassword">
                </TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">用户姓名：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id=user_name style="WIDTH: 130px" type=text name="userName">
                </TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">验证码：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id=txtcode style="WIDTH: 130px" name=txtcode>
                </TD>
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD>
                	<INPUT id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  type=image src="images/login_button.gif" name=btn> 
              	</TD>
              </TR>
             </TBODY>
            </TABLE>
           </TD>
          </TR>
         </TBODY>
        </TABLE>
       </TD>
      </TR>
  <TR>
    <TD>
    	<IMG src="images/login_3.jpg" border=0>
    </TD>
   </TR>
  </TBODY>
 </TABLE>
</DIV>
</DIV>

</FORM>
</BODY>
</HTML>

