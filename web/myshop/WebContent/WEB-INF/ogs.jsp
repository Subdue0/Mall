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
<div class="row">
	<div class="col-lg-12">
		<div class="panel-heading">配件清单</div>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>编号</th>
					<th>名称</th>
					<th>数量</th>
					<th>合计</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ogs}" var="og">
					<tr>
						<td>${og.good_id}</td>
						<td>${og.good_name}</td>
						<td>${og.good_num}</td>
						<td>${og.total_price }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>