<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>首页随便看看</title>
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
<jsp:include page="./common/static_resource.jsp" />

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babyFirst.js"></script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.core.js"></script>

</head>
<body>

	<jsp:include page="./common/head.jsp" />

	<div id="content_wrap" class="content_wrap">
		<div id="content" class="content">
			<div class="nav_section">
				<ul>
					<li class="first">你也许应该看看:</li>
					<li><a href="#" class="zTreeInfoBtn selected"
						onclick="return false;">简述</a></li>
					<li><a href="#" class="licenseBtn" onclick="return false;">授权许可</a></li>
					<li><a href="#" class="contactBtn" onclick="return false;">联系方式</a></li>
					<li><a href="#" class="linksBtn" onclick="return false;">友情链接</a></li>
				</ul>
			</div>

			<div class="siteTag tag_license" alt=""></div>

			<div id="contentBox" class="contentBox round clearfix">
				<div class="row">
					<div class="col-xs-4">
						<div class="input-group">
							<span class="input-group-addon">分组名称</span> <select
								class="form-control" id="userAuthType">
								<option value="ALL">全部</option>
								<option value="admin">管理员</option>
								<option value="normal">普通用户</option>
								<option value="guest">游客</option>
								<!-- 
                			<#list userTypes as userType>
                				<option value="${userType.value}" >${userType.description}</option>
                			</#list>
                			 -->
							</select>
						</div>
					</div>
					<div class="col-xs-2">
						<button class="btn btn-block btn-info" id="searchBtn">搜索</button>
					</div>
					<div class="col-xs-2">
						<button class="btn btn-block btn-success" id="registBtn"
							type="button">+新增用户</button>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-body">
								<table id="user_list" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th name="id">标准ID</th>
											<th name="userType">用户类型</th>
											<th name="loginName">登录名称</th>
											<th name="realName">正式名称</th>
											<th name="email">邮箱</th>
											<th name="mobile">手机号</th>
											<th name="handleMsg">操作</th>

										</tr>
									</thead>
									<tbody></tbody>
									<tfoot></tfoot>
								</table>
							</div>
						</div>
					</div>
				</div>



			</div>
			<div class="clear"></div>
		</div>
	</div>


	<jsp:include page="./common/foot.jsp" />

	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>



	<script type="text/javascript">
		$(function() {

			var userTable = $("#user_list")
					.dataTable(
							{
								"deferRender" : true,
								"processing" : true,
								"serverSide" : true,
								"ajax" : {
									url : base_url
											+ "/user/pageUsersByType.json",
									data : function(d) {
										var obj = {};
										obj.userType = $('#userAuthType').val();
										obj.start = d.start;
										obj.limit = d.length;
										return obj;
									}
								},
								"searching" : false,
								"ordering" : false,
								//"scrollX": false,
								"columns" : [
										{
											"data" : 'id',
											"bSortable" : false,
											"visible" : true
										},
										{
											"data" : 'userType',
											"bSortable" : false,
											"visible" : true
										},
										{
											"data" : 'loginName',
											"bSortable" : false,
											"visible" : true
										},
										{
											"data" : 'realName',
											"visible" : true
										},
										{
											"data" : 'email',
											"visible" : true
										},
										{
											"data" : 'mobile',
											"visible" : true
										},
										{
											"data" : 'handleMsg',
											"bSortable" : false,
											"render" : function(data, type, row) {
												// better support expression or string, not function
												return function() {

													var temp = '<button class="btn btn-primary btn-xs detail"  type="button"  _id="'+ row.id +'"">详情</button> &nbsp; &nbsp;';
													return temp;
												}
											}
										} ],
								"language" : {
									"sProcessing" : "处理中...",
									"sLengthMenu" : "每页 _MENU_ 条记录",
									"sZeroRecords" : "没有匹配结果",
									"sInfo" : "第 _PAGE_ 页 ( 总共 _TOTAL_ 行 )",
									"sInfoEmpty" : "无记录",
									"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
									"sInfoPostFix" : "",
									"sSearch" : "搜索:",
									"sUrl" : "",
									"sEmptyTable" : "表中数据为空",
									"sLoadingRecords" : "载入中...",
									"sInfoThousands" : ",",
									"oPaginate" : {
										"sFirst" : "首页",
										"sPrevious" : "上页",
										"sNext" : "下页",
										"sLast" : "末页"
									},
									"oAria" : {
										"sSortAscending" : ": 以升序排列此列",
										"sSortDescending" : ": 以降序排列此列"
									}
								}
							});

			$('#user_list').on('click', '.detail', function() {
				var _id = $(this).attr('_id');
				window.open(base_url + '/user/detail?id=' + _id);
				return;

			});
			// 搜索按钮
			$('#searchBtn').on('click', function() {
				userTable.fnDraw();
			});

			// 注册按钮
			$('#registBtn').on('click', function() {
				$('#registModal').modal({
					backdrop : false,
					keyboard : false
				}).modal('show');
			});

			var addModalValidate = $("#registModal .form")
					.validate(
							{
								errorElement : 'span',
								errorClass : 'help-block',
								focusInvalid : true,
								rules : {
									loginName : {
										required : true,
										rangelength : [ 4, 16 ]
									},
									password : {
										required : true,
										rangelength : [ 6, 12 ]
									},
									password2 : {
										required : true,
										//					digits:true,
										rangelength : [ 6, 12 ],
										equalTo : "#registModal [name='password']"
									}
								},
								messages : {
									loginName : {
										required : "请输入“登录名称”",
										rangelength : "名称长度4~16位",
									},
									password : {
										required : "请输入“密码”",
										rangelength : "长度限制为6~12"
									},
									password2 : {
										required : "请再次输入“密码”",
										//					digits: "请输入整数",
										rangelength : "长度限制为6~12",
										equalTo : "请输入相同的密码"
									}
								},
								highlight : function(element) {
									$(element).closest('.form-group').addClass(
											'has-error');
								},
								success : function(label) {
									label.closest('.form-group').removeClass(
											'has-error');
									label.remove();
								},
								errorPlacement : function(error, element) {
									element.parent('div').append(error);
								},

								submitHandler : function(form) {
									$
											.post(
													base_url
															+ "/user/regist.json",
													$("#registModal .form")
															.serialize(),
													function(result, status) {

														if (result.exception) {
															ComAlert
																	.show(
																			2,
																			"报错啦！"
																					+ result.exception);
														} else {
															var loginName = $(
																	"#registModal [name='loginName']")
																	.val();
															setTimeout(
																	function() {
																		ComAlert
																				.show(
																						1,
																						"保存成功",
																						function() {
																							$(
																									"#loginForm [name='user']")
																									.val(
																											loginName);
																						});
																	}, 315);
															$('#registModal')
																	.modal(
																			'hide');
														}

													});
								}
							});
			$("#registModal").on('hide.bs.modal', function() {
				$("#registModal .form")[0].reset();
				addModalValidate.resetForm();
				$("#registModal .form .form-group").removeClass("has-error");
			});

			// jquery.validate 自定义校验 “英文字母开头，只含有英文字母、数字和下划线”
			jQuery.validator.addMethod("equalTo", function(value, element,
					param) {

				var pwd = $(param).val();

				return pwd == value;
			}, "两次密码不一致");

		});
	</script>

	<!-- 新增.模态框 -->
	<div class="modal fade" id="registModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog ">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">注册新账户</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal form" role="form">
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">登录名<font
								color="red">*</font></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="loginName"
									placeholder="请输入“登录名称”" maxlength="50">
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
							<label for="firstname" class="col-sm-2 control-label">用户类型<font
								color="red">*</font></label>
							<div class="col-sm-8">
								<select class="form-control" name="userType">
									<option value=""></option>
									<option value="admin">管理员</option>
									<option value="normal">普通用户</option>
									<option value="guest">游客</option>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="email"
									placeholder="请输入邮箱(仅作交流)" maxlength="200">
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">电话号码</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="mobile"
									placeholder="请输入手机号码(仅作交流)" maxlength="200">
							</div>
						</div>

						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">正式名称</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="realName"
									placeholder="请输入公司名称(仅作交流)" maxlength="200">
							</div>
						</div>

						<hr>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">
								<button type="submit" class="btn btn-primary">保存</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



</body>
</html>