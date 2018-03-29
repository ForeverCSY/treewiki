<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.wjs.treewiki.constant.Dictionary"%>
<%@page import="com.wjs.treewiki.model.auth.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>用户详情</title>
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
<script type="text/javascript">
	var lang = "cn";
</script>
<jsp:include page="../common/static_resource.jsp" />

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babyFirst.js"></script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.core.js"></script>

</head>
<body>
	<jsp:include page="../common/head.jsp" />

	
	<% 
		Object userObj = request.getAttribute("userInfo");
		User userInfo = (User)userObj;
	%>
	<div id="content_wrap" class="content_wrap">
		<div id="content" class="content">


			<div id="contentBox" class="contentBox round clearfix">

				<div class="" id="registModal" tabindex="-1" role=""
					aria-hidden="true">
					<div class="modal-dialog ">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" name="title">信息管理-第(${userInfo.id + 1})位用户</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal form" role="form">
									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">用户类型<font
											color="red">*</font></label>
										<div class="col-sm-8">
											<% if(Dictionary.UserType.ADMIN.equals(userInfo.getUserType())){
												%>
											
											<select
												class="form-control" name="userType"
												paramVal="${groupId}">
												
					                            <option value="admin" <%=Dictionary.UserType.ADMIN.equals(userInfo.getUserType())? "selected" : "" %>>管理员</option>
					                            <option value="normal" <%=Dictionary.UserType.NORMAL.equals(userInfo.getUserType())? "selected" : "" %>>普通用户</option>
					                            <option value="guest" <%=Dictionary.UserType.GUEST.equals(userInfo.getUserType())? "selected" : "" %>>游客</option>

											</select> 
											<% } else {%>
											<input type="text" class="form-control"
												name="userType" placeholder="请输入“登录名称”" maxlength="50"
												value="${userInfo.userType}" readonly="readonly">
											<%} %>
										</div>
									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">登录名<font
											color="red">*</font></label>
										<div class="col-sm-8">
											<input type="text" class="form-control" name="loginName"
												placeholder="请输入“登录名称”" maxlength="50"
												value="${userInfo.loginName}"
												<%=StringUtils.isEmpty(userInfo.getLoginName())? "" : "readonly" %>
											>
										</div>
									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">密码<font
											color="red">*</font></label>
										<div class="col-sm-8">
											<input type="password" class="form-control" name="password"
												maxlength="200">
										</div>
									</div>
									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">确认密码<font
											color="red">*</font></label>
										<div class="col-sm-8">
											<input type="password" class="form-control" name="password2"
												maxlength="200">
										</div>
									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">昵称</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" name="realName"
												placeholder="请输入昵称" maxlength="200"
												value="${userInfo.realName}">
										</div>
									</div>

									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">邮箱</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" name="email"
												placeholder="请输入邮箱(仅作交流)" maxlength="200"
												value="${userInfo.email}">
										</div>
									</div>
									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">电话号码</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" name="mobile"
												placeholder="请输入手机号码(仅作交流)" maxlength="200"
												value="${userInfo.mobile}">
										</div>
									</div>
									<hr>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-6">
											<button type="submit" class="btn btn-primary">保存</button>
											<input type="hidden" class="form-control" name="id"
												value="${userInfo.id}">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="clear"></div>
		</div>
	</div>


	<jsp:include page="../common/foot.jsp" />

	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>



	<script type="text/javascript">
	$(function(){
		
		var addModalValidate = $("#registModal .form").validate({
			errorElement : 'span',
			errorClass : 'help-block',
			focusInvalid : true,
			rules : {
				loginName : {
					required : true,
					rangelength:[4,16]
				},
				password : {
					required : false,
					rangelength:[6, 12]
				},
				password2 : {
					required : false,
//					digits:true,
					rangelength:[6, 12],
					equalTo: "#registModal [name='password']"
				}
			},
			messages : {
				loginName : {
					rangelength:"名称长度4~16位",
				},
				password : {
					rangelength:"长度限制为6~12"
				},
				password2 : {
					required :"请再次输入“密码”",
//					digits: "请输入整数",
					rangelength: "长度限制为6~12",
					equalTo: "请输入相同的密码"
				}
			},
			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},
			errorPlacement : function(error, element) {
				element.parent('div').append(error);
			},
			
			submitHandler : function(form) {
				$.post(base_url + "/user/modify.json",  $("#registModal .form").serialize(), function(data, status) {

					if (data.exception) {
						ComAlert.show(2, data.exception);
					} else {
						setTimeout(function () {
							ComAlert.show(1, "保存成功", function(){
								$('#registModal').modal('hide');
							});
						}, 315);
						
					}
				});
			}
		});
		$("#registModal").on('hide.bs.modal', function () {
			$("#registModal .form")[0].reset();
			addModalValidate.resetForm();
			$("#registModal .form .form-group").removeClass("has-error");
		});
		
		// jquery.validate 自定义校验 “英文字母开头，只含有英文字母、数字和下划线”
		jQuery.validator.addMethod("equalTo", function(value, element, param) {

			var pwd = $(param).val();
			
			return pwd == value; 
		}, "两次密码不一致");

		
	});
	</script>




<script type="text/javascript">
	$('#js_selected_user').addClass('selected')
</script>
</body>
</html>