<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jquery -->
<script
	src="<%=request.getAttribute("basePath")%>/static/adminlte/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="<%=request.getAttribute("basePath")%>/static/plugins/jquery/jquery.validate.min.js"></script>

<!-- bootstrap -->
<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/adminlte/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/adminlte/bootstrap/js/bootstrap.min.js"></script>



<!-- daterangepicker -->
<script src="<%=request.getAttribute("basePath")%>/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="<%=request.getAttribute("basePath")%>/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getAttribute("basePath")%>/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/daterangepicker/daterangepicker-bs3.css">



<!-- my- common -->
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