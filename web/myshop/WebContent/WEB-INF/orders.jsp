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
<div id="wrapper">
  <!-- 导航栏部分 -->
  <nav class="navbar navbar-default navbar-static-top" role="navigation"
		 style="margin-bottom: 0">
	<div class="navbar-header">
		<a class="navbar-brand" href="<%=basePath%>good/list.action">电脑配件销售管理系统</a>
	</div>
	<!-- 导航栏右侧图标部分 -->
	<ul class="nav navbar-top-links navbar-right">
	    <!-- 邮件通知 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-envelope fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-messages">
				<li>
				    <a href="#">
						<div>
							<strong>张经理</strong>
							<span class="pull-right text-muted">
								<em>昨天</em>
							</span>
						</div>
						<div>今天晚上开会，讨论一下下个月工作的事...</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a class="text-center" href="#">
				        <strong>查看全部消息</strong>
						<i class="fa fa-angle-right"></i>
				    </a>
				</li>
			</ul>
		</li>
		<!-- 邮件通知 end -->
		<!-- 任务通知 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
			    <i class="fa fa-tasks fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-tasks">
				<li>
				    <a href="#">
						<div>
							<p>
								<strong>任务 1</strong> 
								<span class="pull-right text-muted">完成40%</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">完成40%</span>
								</div>
							</div>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<p>
								<strong>任务 2</strong> 
								<span class="pull-right text-muted">完成20%</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
									style="width: 20%">
									<span class="sr-only">完成20%</span>
								</div>
							</div>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a class="text-center" href="#"> 
				        <strong>查看所有任务</strong>
						<i class="fa fa-angle-right"></i>
				    </a>
				</li>
			</ul> 
		</li>
		<!-- 任务通知 end -->
		<!-- 消息通知 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-bell fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-comment fa-fw"></i> 新回复 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-envelope fa-fw"></i> 新消息 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-tasks fa-fw"></i> 新任务 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-upload fa-fw"></i> 服务器重启 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a class="text-center" href="#"> 
				        <strong>查看所有提醒</strong>
						<i class="fa fa-angle-right"></i>
				    </a>
				</li>
			</ul> 
		</li>
		<!-- 消息通知 end -->
		<!-- 用户信息和系统设置 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#"><i class="fa fa-user fa-fw"></i>
				               用户：${USER_SESSION.user_name}
				    </a>
				</li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a></li>
				<li class="divider"></li>
				<li>
					<a href="${pageContext.request.contextPath }/login.action">
					<i class="fa fa-sign-out fa-fw"></i>退出登录
					</a>
				</li>
			</ul> 
		</li>
		<!-- 用户信息和系统设置结束 -->
	</ul>
	<!-- 左侧显示列表部分 start-->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="查询内容...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search" style="padding: 3px 0 3px 0;"></i>
							</button>
						</span>
					</div> 
				</li>
				<li>
				    <a href="${pageContext.request.contextPath }/good/list.action" class="active">
				      <i class="fa fa-edit fa-fw"></i> 配件管理
				    </a>
				</li>
				<li>
				    <a href="${pageContext.request.contextPath }/order/toOrders.action" class="active">
				      <i class="fa fa-tasks fa-fw" ></i> 订单管理
				    </a>
				</li>
				<li>
				    <a href="${pageContext.request.contextPath }/customer/list.action" class="active">
				      <i class="fa fa-user fa-fw" ></i> 用户管理
				    </a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 左侧显示列表部分 end--> 
  </nav>
    <!-- 客户列表查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">订单管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="get" action="${pageContext.request.contextPath }/order/list.action">
					<div class="form-group">
						<label for="custName">客户名</label> 
						<input type="text" class="form-control" id="custName" value="${custName }" name="custName" />
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">订单信息列表</div>
					<!-- /.panel-heading -->
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>订单号</th>
								<th>用户名</th>
								<th>联系方式</th>
								<th>收件人</th>
								<th>收件人电话</th>
								<th>收件人地址</th>
								<th>创建时间</th>
								<th>订单状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.rows}" var="row">
								<tr>
									<td>${row.order_id}</td>
									<td>${row.cust_name}</td>
									<td>${row.cust_phone}</td>
									<td>${row.rec_name}</td>
									<td>${row.rec_phone}</td>
									<td>${row.rec_address}</td>
									<td>${row.order_time}</td>
									<td>${row.order_status}</td>
									<td>
										<a href="${pageContext.request.contextPath }/order/good.action?id=${row.order_id}" class="btn btn-primary btn-xs">打印清单</a>
										<a href="#" class="btn btn-danger btn-xs" onclick="deleteOrder(${row.order_id})">删除订单</a>
										<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#orderEditDialog" onclick="editOrder(${row.order_id})">修改</a>
										<a href="#" class="btn btn-primary btn-xs" onclick="setState(${row.order_id})">发货</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
	</div>
</div>
<!-- 修改配件模态框 -->
<div class="modal fade" id="orderEditDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改订单信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="edit_order_form">
					<input type="hidden" id="edit_orderId" name="order_id"/>
					<div class="form-group">
						<label for="edit_recName" class="col-sm-2 control-label">收件人</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_recName" placeholder="收件人" name="rec_name" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_recPhone" class="col-sm-2 control-label">联系方式</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_recPhone" placeholder="电话" name="rec_phone" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_recAddress" class="col-sm-2 control-label">收件地址</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_recAddress" placeholder="地址" name="rec_address" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_orderState" class="col-sm-2 control-label">订单状态</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_orderState" placeholder="订单状态" name="order_status" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="updateOrder()">保存修改</button>
			</div>
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
	// 通过id获取修改的配件信息
	function editOrder(id) {
	    $.ajax({
	        type:"get",
	        url:"<%=basePath%>order/getOrderById.action",
	        data:{"id":id},
	        success:function(data) {
	            $("#edit_orderId").val(data.order_id);
	            $("#edit_recName").val(data.rec_name);
	            $("#edit_recPhone").val(data.rec_phone);
	            $("#edit_recAddress").val(data.rec_address);
	            $("#edit_orderState").val(data.order_status);
	        }
	    });
	}
	// 执行修改订单操作
	function updateOrder() {
		$.post("<%=basePath%>order/update.action",$("#edit_order_form").serialize(),
		function(data){
			if(data =="OK"){
				alert("配件信息更新成功！");
				window.location.reload();
			}else{
				alert("配件信息更新失败！");
				window.location.reload();
			}
		});
	}
	//设置状态
    function setOrder(id){
    	$.post("<%=basePath%>order/setorder.action",{"id":id});
    	alert("订单已取消");
    	window.location.reload();
    }
	function setState(id){
		$.post("<%=basePath%>order/setstate.action",{"id":id});
		alert("订单已发货");
		window.location.reload();
	}
	// 删除配件
	function deleteOrder(id) {
	    if(confirm('确实要删除该订单吗?')) {
		$.post("<%=basePath%>order/delete.action",{"id":id},
		function(data){
	            if(data =="OK"){
	                alert("订单删除成功！");
	                window.location.reload();
	            }else{
	                alert("订单配件失败！");
	                window.location.reload();
	            }
	        });
	    }
	}
</script>
</body>
</html>