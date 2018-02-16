<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/adminlte/bootstrap/css/bootstrap.min.css">
<script
	src="<%=request.getAttribute("basePath")%>/static/adminlte/plugins/jQuery/jQuery-2.1.4.min.js"></script>


<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/common.alert.1.js"></script>
	
	
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/common.1.js"></script>



<script>


	var base_url = '<%=request.getAttribute("basePath") %>';
	var redirectURL = $.getUrlParam('redirectURL');
	
	var logonInfoJson = '<%=request.getAttribute("logonInfo") %>';
	var logonInfo = $.parseJSON(logonInfoJson);
	
	$(function(){
		// logout
		$("#logoutBtn").click(function(){
			ComConfirm.show("确认注销登录?", function(){
				$.post(base_url + "/logon/out", function(data, status) {
					if (data.code == "succ") {
						ComAlert.show(1, "注销成功", function(){
							window.location.href = base_url + "/";
						});
					} else {
						ComAlert.show(1, data.resultMsg);
					}
				});
			});
		});
	});
</script>