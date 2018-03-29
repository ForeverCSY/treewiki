<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>首页</title>
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


			<div id="contentBox" class="contentBox round clearfix"
				style="height: 569px; margin-bottom: 0px;">
				<div id="zTreeInfo" class="zTreeInfo"
					style="top: 0px; left: 0px; display: block;">
					<div class="title">
						<h1>网金社treewiki 简介</h1>
						<br>
						<h4>&nbsp;&nbsp;&nbsp;&nbsp;treewiki 拥有庞大的网金社平台知识储备，提供详细的字段解释，各项配置教程。</h4>
						<h4>&nbsp;&nbsp;&nbsp;&nbsp;加入网金社，圆你的金融梦!</h4>
					</div>

					<!-- <div id="zTreeInfo-left" class="zTreeInfo-left"></div>
					<div id="zTreeInfo-right" class="zTreeInfo-right">
						<ul>
							<li><span>zTree v3.0 将核心代码按照功能进行了分割，不需要的代码可以不用加载</span></li>
							<li><span>采用了 延迟加载 技术，上万节点轻松加载，即使在 IE6 下也能基本做到秒杀</span></li>
							<li><span>兼容 IE、FireFox、Chrome、Opera、Safari 等浏览器</span></li>
							<li><span>支持 JSON 数据</span></li>
							<li><span>支持静态 和 Ajax 异步加载节点数据</span></li>
							<li><span>支持任意更换皮肤 / 自定义图标（依靠css）</span></li>
							<li><span>支持极其灵活的 checkbox 或 radio 选择功能</span></li>
							<li><span>提供多种事件响应回调</span></li>
							<li><span>灵活的编辑（增/删/改/查）功能，可随意拖拽节点，还可以多节点拖拽哟</span></li>
							<li><span>在一个页面内可同时生成多个 Tree 实例</span></li>
							<li><span>简单的参数配置实现 灵活多变的功能</span></li>
						</ul>
					</div> -->
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	
	<jsp:include page="./common/foot.jsp" />

	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>
<script type="text/javascript">
	$('#js_selected_home').addClass('selected')
</script>
</body>
</html>