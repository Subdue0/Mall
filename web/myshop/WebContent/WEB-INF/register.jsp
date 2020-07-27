<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myshop" uri="http://myshop.com/common/"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() 
	                   + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>配件管理系统</title>
		<!-- 引入css样式文件 -->
		<!-- Bootstrap Core CSS -->
		<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" />
		<!-- MetisMenu CSS -->
		<link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet" />
		<!-- DataTables CSS -->
		<link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet" />
		<!-- Custom CSS -->
		<link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet" />
		<!-- Custom Fonts -->
		<link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/boot-crm.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">
	<h1>新用户注册</h1>
	<div class="row">
		<div class="col-lg-6">
			<form action="${pageContext.request.contextPath }/login.action" class="form-horizontal" id="new_customer_form">
			<div class="form-group">
				<div>
					<label>账号</label>
					<input class="form-control" id="new_custId" type="text" name="cust_id" placeholder="输入账号"/>
				</div>
			</div>
			<div class="form-group">
				<div>
					<label>姓名</label>
					<input class="form-control" id="new_custName" type="text" name="cust_name" placeholder="输入姓名"/>
				</div>
			</div>
			<div class="form-group">
				<div>
					<label>密码</label>
					<input class="form-control" id="new_custPassword" type="password" name="cust_password" placeholder="输入密码"/>
				</div>
			</div>
			<div class="form-group">
				<div>
					<label>电话</label>
					<input class="form-control" id="new_custPhone" type="text" name="cust_phone" placeholder="输入电话"/>
				</div>
			</div>
			<div class="form-group">
				<div>
					<label>地址</label>
					<input class="form-control" id="new_custAddress" type="text" name="cust_address" placeholder="输入地址"/>
				</div>
				
			</div>
			<button type="submit" class="btn btn-success" onclick="createCustomer()">提交</button>
			</form>
		</div>
	</div>
</div>
<!-- 引入js文件 -->
<!-- jQuery -->
<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>
<!-- DataTables JavaScript -->
<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>js/sb-admin-2.js"></script>
<script type="text/javascript">
	// 创建客户
	function createCustomer() {
	$.post("<%=basePath%>customer/create.action",
	$("#new_customer_form").serialize(),function(data){
	        if(data =="OK"){
	            alert("客户创建成功！");
	        }else{
	            alert("客户创建失败！");
	            window.location.reload();
	        }
	    });
	}
</script>
</body>
</html>