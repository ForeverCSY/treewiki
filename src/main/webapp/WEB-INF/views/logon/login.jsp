<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="description" content="treewiki_content">
<meta name="keywords"
	content="jQuery, javascript, Tree, plugIn, 树, 插件, 树插件, Web, Web前端">
<title>treewiki</title>
<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/css/common.css"
	type="text/css">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/css/zTreeStyle/zTreeStyleForDemo.css"
	type="text/css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/adminlte/dist/css/AdminLTE-local.min.css">



<jsp:include page="../common/static_resource.jsp" />
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babyFirst.js"></script>
<script type="text/javascript">
	var lang = "cn";
</script>


<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.core.js"></script>

</head>
<body>

	<jsp:include page="../common/head.jsp" />

	<div id="content_wrap" class="content_wrap">
		<div id="content" class="content">

			<div class="login-box">
				<form id="loginForm" method="post" role="form"
					novalidate="novalidate">
					<div class="login-box-body">
						<!--  -->
						<p class="login-box-msg">任务调度中心</p>
						<div class="form-group has-feedback">
							<input type="text" name="userName" class="form-control"
								placeholder="请输入登陆账号"> <span
								class="glyphicon glyphicon-envelope form-control-feedback"></span>
						</div>

						<div class="form-group has-feedback">
							<input type="password" name="password" class="form-control">
							<span class="glyphicon glyphicon-lock form-control-feedback"></span>
						</div>
						<div class="row">
							<!-- 
									<div class="col-xs-8">
										<div class="checkbox icheck">
											<label> <input type="checkbox" name="ifRemember">
												记住密码</input>
											</label>
										</div>
									</div>
								 -->

							<div class="col-xs-4">
								<button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>




	</div>
	<div class="clear"></div>
	</div>
	</div>


	<jsp:include page="../common/foot.jsp" />
	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/logon/login.1.js"></script>

	<script
		src="<%=request.getAttribute("basePath")%>/static/plugins/jquery/jquery.validate.min.js"></script>
	<script
		src="<%=request.getAttribute("basePath")%>/static/plugins/iCheck/icheck.min.js"></script>
		

	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>
</body>
</html>