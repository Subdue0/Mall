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
    <!-- 配件列表查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">电脑配件管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="get" action="${pageContext.request.contextPath }/good/list.action">
					<div class="form-group">
						<label for="goodName">配件名称</label> 
						<input type="text" class="form-control" id="goodName" value="${goodName }" name="goodName" />
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
			</div>
		</div>
		<a href="#" class="btn btn-primary" data-toggle="modal" 
		           data-target="#newCustomerDialog" onclick="clearGood()">配件上架</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">配件信息列表</div>
					<!-- /.panel-heading -->
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>编号</th>
								<th>图片</th>
								<th>配件名称</th>
								<th>配件价格</th>
								<th>配件类型</th>
								<th>配件详情</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.rows}" var="row">
								<tr>
									<td>${row.good_id}</td>
									<td>
										<img width="50px" height="50px" src="${row.pic_url }"/>
									</td>
									<td>${row.good_name}</td>
									<td>${row.good_price}</td>
									<td>${row.type_name}</td>
									<td>${row.good_detail}</td>
									<td>
										<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#goodEditDialog" onclick="editGood(${row.good_id})">修改</a>
										<a href="#" class="btn btn-danger btn-xs" onclick="deleteGood(${row.good_id})">删除</a>
										<a href="#" class="btn btn-primary btn-xs" onclick="setGood(${row.good_id})">售罄</a>
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
	<!-- 配件列表查询部分  end-->
</div>
<!-- 创建配件模态框 -->
<div class="modal fade" id="newCustomerDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新建配件信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="new_good_form">
					<div class="form-group">
						<label for="new_goodId" class="col-sm-2 control-label">
						    配件编号
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_goodId" placeholder="配件编号" name="good_id" />
						</div>
					</div>
					<div class="form-group">
						<label for="new_goodName" class="col-sm-2 control-label">
						    配件名称
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_goodName" placeholder="配件名称" name="good_name" />
						</div>
					</div>
					<div class="form-group">
						<label for="new_goodPrice" class="col-sm-2 control-label">
						    配件价格
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_goodPrice" placeholder="配件价格" name="good_price" />
						</div>
					</div>
					<div class="form-group">
						<label for="new_typeId" class="col-sm-2 control-label">
						    配件类型
						</label>
						<div class="col-sm-10">
							 <input type="text" class="form-control" id="new_typeId" placeholder="配件类型" name="type_id" /> 
							<!--<select class="form-control" id="new_typeId" name="type_id">
								<option>处理器</option>
								<option>显卡</option>
								<option>内存</option>
								<option>硬盘</option>
							</select>-->
						</div>
					</div>
					<div class="form-group">
						<label for="new_goodDetail" class="col-sm-2 control-label">
						    配件详情
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_goodDetail" placeholder="配件详情" name="good_detail" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createGood()">创建客户</button>
			</div>
		</div>
	</div>
</div>
<!-- 修改配件模态框 -->
<div class="modal fade" id="goodEditDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改配件信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="edit_good_form">
					<input type="hidden" id="edit_goodId" name="good_id"/>
					<div class="form-group">
						<label for="edit_goodName" class="col-sm-2 control-label">配件名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_goodName" placeholder="配件名称" name="good_name" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_goodPrice" class="col-sm-2 control-label">配件价格</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_goodPrice" placeholder="配件价格" name="good_price" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_typeId" class="col-sm-2 control-label">配件类型</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_typeId" placeholder="配件类型" name="type_id" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_goodDetail" class="col-sm-2 control-label">配件详情</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_goodDetail" placeholder="配件详情" name="good_detail" />
						</div>
					</div>
					<div class="form-group">
						<label for="edit_picUrl" class="col-sm-2 control-label">配件图片</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_picUrl" placeholder="配件图片" name="pic_url" />
						</div>
					</div>
				</form>
				<form action="${pageContext.request.contextPath }/fileUpload.action" class="form-horizontal" method="post" enctype="multipart/form-data" onsubmit="return setImage()">
					<div class="form-group">
						<label for="file" class="col-sm-2 control-label">上传图片</label>
						<div class="col-sm-10">
							<input id="file" type="file" multiple="multiple" name="uploadfile"/>
							<input type="submit" value="上传"/>
						</div>
					</div>
					<%-- <div class="form-group">
						<label for="file" class="col-sm-2 control-label">图片预览</label>
						<div class="col-sm-10">
							<img width="100px" height="100px" src="${picture }">
							<img width="100px" height="100px" src="<%=basePath%>images/i32.jpg">
							<img width="100px" height="100px" src="<%=basePath%>images/i33.jpg">
						</div>
					</div> --%>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="updateGood()">保存修改</button>
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
<!-- 编写js代码 -->
<script type="text/javascript">
	//清空新建配件窗口中的数据
	function clearGood() {
		$("#new_goodId").val("");
	    $("#new_goodName").val("");
	    $("#new_goodPrice").val("");
	    $("#new_typeId").val("");
	    $("#new_goodDetail").val("");
	}
	// 创建配件
	function createGood() {
	$.post("<%=basePath%>good/create.action",
	$("#new_good_form").serialize(),function(data){
	        if(data =="OK"){
	            alert("配件创建成功！");
	            window.location.reload();
	        }else{
	            alert("配件创建失败！");
	            window.location.reload();
	        }
	    });
	}
	// 通过id获取修改的配件信息
	function editGood(id) {
	    $.ajax({
	        type:"get",
	        url:"<%=basePath%>good/getGoodById.action",
	        data:{"id":id},
	        success:function(data) {
	            $("#edit_goodId").val(data.good_id);
	            $("#edit_goodName").val(data.good_name);
	            $("#edit_goodPrice").val(data.good_price);
	            $("#edit_typeId").val(data.type_id);
	            $("#edit_goodDetail").val(data.good_detail);
	            $("#edit_picUrl").val(data.pic_url);
	        }
	    });
	}
    // 执行修改配件操作
	function updateGood() {
		$.post("<%=basePath%>good/update.action",$("#edit_good_form").serialize(),
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
    
    //设置库存
    function setGood(id){
    	$.post("<%=basePath%>good/setgood.action",{"id":id});
    	alert("配件已售空");
    	window.location.reload();
    }
    
	// 删除配件
	function deleteGood(id) {
	    if(confirm('确实要删除该配件吗?')) {
		$.post("<%=basePath%>good/delete.action",{"id":id},
		function(data){
	            if(data =="OK"){
	                alert("配件删除成功！");
	                window.location.reload();
	            }else{
	                alert("删除配件失败！");
	                window.location.reload();
	            }
	        });
	    }
	}
	//文件上传
 	function setImage(){
		var file=document.getElementById("file").value;
		if(file.length==0||file==""){
			alert("请选择上传文件");
			return false;
		}
		alert("上传成功");
		return true;
	} 
</script>
</body>
</html>