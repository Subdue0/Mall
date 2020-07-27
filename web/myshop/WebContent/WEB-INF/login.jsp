<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="MSHTML 6.00.2600.0" name=GENERATOR>
<title>登录界面</title>
<link href="${pageContext.request.contextPath}/css/style.css" type=text/css rel=stylesheet>
<link href="${pageContext.request.contextPath}/css/boot-crm.css" type=text/css rel=stylesheet>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js">
</script>
<script>
	function check(){
		var username=$("#username").val();
		var password=$("#password").val();
		if(username==""||password==""){
			$("#message").text("账号或密码不能为空！");
			return false;
		}
		return true;
	}
</script>
</head>
<body leftMargin=0 topMargin=0 marginwidth="0" marginheight="0" background="${pageContext.request.contextPath }/images/background.jpg">
<div ALIGN="center">
<table border="0" width="1140px" cellspacing="0" cellpadding="0" id="table1">
	<tr>
		<td height="93"></td>
		<td></td>
	</tr>
	<tr>
		<td width="700" height="700"></td>
		<td class="login_msg" width="400" align="center">
			<!-- 控制当前标签居中 -->
			<fieldset style="width:auto;margin:0px auto;">
				<legend>
					<font style="font-size:15px" face="宋体">硬件商店管理系统</font>
				</legend>
				<font color="red"><span id="message">${msg }</span></font>
				<form action="${pageContext.request.contextPath }/login.action" method="post" onsubmit="return check()">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
					账&nbsp;号：<input id="username" type="text" name="username"/><br/><br/>
					密&nbsp;码：<input id="password" type="password" name="password"/><br/><br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<center>
						<input type="submit" value="登录"/>
						<a href="${pageContext.request.contextPath }/register.action">注册</a>
					</center>
				</form>
			</fieldset>
		</td>
	</tr>
</table>
</div>
</body>
</html>