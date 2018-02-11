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
<link rel="shortcut icon" href="/favicon.ico">


<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/css/common.css"
	type="text/css">

<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<style type="text/css">
.ztree li a.level0 {width:200px;height: 20px; text-align: center; display:block; background-color: #0B61A4; border:1px silver solid;}
.ztree li a.level0.cur {background-color: #66A3D2; }
.ztree li a.level0 span {display: block; color: white; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
.ztree li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;display:none;}
.ztree li span.button.switch.level0 {display:none;}
</style>

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babyFirst.js"></script>
<script type="text/javascript">
	var lang = "cn";
</script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery-1.6.2.min.js"></script>


<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.core.js"></script>

</head>
<body>

	 <jsp:include page="./common/head.jsp"/> 
	
	
	<div id="content_wrap" class="content_wrap">
		<div id="content" class="content">
			<div class="nav_section">
				<ul>
				</ul>
			</div>

			<div class="siteTag tag_donate" alt=""></div>
			<div id="contentBox" class="contentBox round clearfix">
				<div id="demoContent" class="demoContent">
					<div id="demo_menu" class="demo_menu left ">
						<ul id="menuTree" class="ztree">
						</ul>
					</div>
					<div id="demo_viewer" class="demo_viewer right">
						<h1>默认文案</h1>
					</div>
				</div>

			</div>
			<div class="clear"></div>
		</div>
	</div>
	<script type="text/javascript">
		var menu_nodes = [{id:1, pId:0, name:"加载中……", open:false}];
		$.ajax({
			url : '/index/getTree.json',
			// data : params,
			type : "POST",
			cache : false,
			success : function(result) {
				if (result.exception) {
					alert("报错啦！" + result.exception);
				} else {
					menu_nodes = result.data;
					demoContent._init();
				}
			},
			error : function(e) {
				alert("未知错误:http状态 >" + e.status);
			}
		});
		
		
		
		
		var demoContent = {
				zTree_Menu : null,
				curMenu : null,
				curDemo : null,
				demoIframe : null,
				_init : function() {
					this.demoIframe = $("#demo_viewer");
					var setting = {
						view : {
							showIcon : this.showIcon,
							showLine : true,
							selectedMulti : false,
							dblClickExpand : false
						},
						data : {
							simpleData : {
								enable : true,
								rootPId : 0
							}
						},
						callback : {
							onNodeCreated : this.onNodeCreated,
							beforeClick : this.beforeClick,
							onClick : this.onClick
						}
					};
					demoContent.zTree_Menu = $.fn.zTree.init($("#menuTree"), $.fn.zTree._z.tools.clone(setting), menu_nodes);
					this.bindEvent();
					demoContent.showContent()
					
					
				},
				bindEvent : function() {
					this.demoIframe.bind("load", demoContent.onload)
				},
				showIcon : function(a, b) {
					return !!b.iconSkin
				},
				showContent : function(a) {
					var b = window.location.href, c = window.location.hash;
					b.indexOf("#") > -1 && ( b = b.substring(0, b.indexOf("#")), c = c.substr(2));
					a || (!a && c.length > 0 && ( a = demoContent.zTree_Menu.getNodeByParam("id", c)), a || ( a = demoContent.zTree_Menu.getNodes()[0].children[0]), demoContent.zTree_Menu.selectNode(a));
					if (a){
						demoContent.curMenu = a.getParentNode();
						//demoContent.demoIframe.attr("src", "demo/" + lang + "/" + a.content + ".html");
						demoContent.print(a.id);
						window.location.href = b + "#_" + a.id;
					}
				
				},
				
				print : function(id){
					$.ajax({
						url : '/content/getByItemId.json',
						data : {
							itemId : id,
						},
						type : "POST",
						cache : false,
						success : function(result) {
							if (result.exception) {
								alert("报错啦！" + result.exception);
							} else {
								if(result.data != ""){

									demoContent.demoIframe.html(result.data);	
								}
							}
						},
						error : function(e) {
							alert("未知错误:http状态 >" + e.status);
						}
					});
				},
				
				onload : function() {
					demoContent.demoIframe.fadeIn("fast");
//					var a = demoContent.demoIframe.contents().find("body").get(0).scrollHeight, b = demoContent.demoIframe.contents().find("html").get(0).scrollHeight, c = Math.max(a, b), a = Math.min(a, b), c = demoContent.demoIframe.height() >= c ? a : c;
//					c < 530 && ( c = 530);
//					ie ? demoContent.demoIframe.height(c) : demoContent.demoIframe.animate({
//						height : c
//					}, {
//						duration : "normal",
//						easing : "swing",
//						complete : null
//					})
				},
				onNodeCreated : function(a, b, c) {
					var d = $("#" + c.tId + "_a");
					c.blank && d.css({
						cursor : "default"
					});
					d.hover(function() {
						demoContent.curMenu != c && d.addClass("cur")
					}, function() {
						demoContent.curMenu != c && d.removeClass("cur")
					})
				},
				beforeClick : function(treeId, node) {
					if (node.level == 0 && demoContent.curMenu != node){
						demoContent.zTree_Menu.expandNode(node, !0);
						demoContent.zTree_Menu.expandNode(demoContent.curMenu, !1);
						if(typeof (demoContent.curMenu) != "undefined" && demoContent.curMenu != null) {
							$("#" + demoContent.curMenu.tId + "_a").removeClass("cur");
						}
						
						$("#" + node.tId + "_a").addClass("cur");
						demoContent.curMenu = node;
					}
					if(node.level == 0){
						// 顶层节点特殊处理，其他的节点的内容显示放在onClick方法中处理。
						demoContent.print(node.id);
					}
					
					var rtn = !node.blank && node.level != 0 && !demoContent.zTree_Menu.isSelectedNode(node);
					//var rtn = !node.blank && !demoContent.zTree_Menu.isSelectedNode(node);
					return rtn;
				},
				onClick : function(event, treeId, treeNode) {
					// 内容显示
					demoContent.showContent(treeNode)
				}
			};


	</script>
	<div id="footer_wrap" class="footer_wrap">
		<div id="footer" class="footer">
			<a href="http://www.treejs.cn/v3/main.php" style="display: inline;"
				title="zTree Home"><div class="footer-logo"></div></a>

			<!-- Site Switcher -->
			<div id="footer_mii" class="footer_mii">
				<a href="javascript:void(0)" target="_blank">Copyright @2010
					zTree 版权所有.</a>
			</div>
			<div id="footer_siteMap" class="footer_siteMap open">
				<div class="footer_siteMap_header" title="站内导航">站内导航</div>
				<ul id="footer_siteMap_ul" class="up" style="display: none;">
					<li><a href="http://www.treejs.cn/v3/main.php#_zTreeInfo"
						class="zTreeInfoBtn">zTree 简介</a></li>
					<li><a href="http://www.treejs.cn/v3/main.php#_license"
						class="licenseBtn">zTree 授权协议</a></li>
					<li><a href="http://www.treejs.cn/v3/donate.php"
						class="donateBtn">捐助 zTree</a></li>
					<li><a href="http://www.treejs.cn/v3/demo.php">zTree v3.5
							Demo 演示</a></li>
					<li><a href="http://www.treejs.cn/v3/api.php">zTree v3.5
							API 文档</a></li>
					<li><a href="http://www.treejs.cn/v3/faq.php">zTree v3.x
							常见问题</a></li>
					<li><a href="http://www.treejs.cn/hunter/index.html"
						target="_blank">zTree v2.6 回忆</a></li>
					<li><a href="http://www.treejs.cn/v3/main.php#_links"
						class="linksBtn">友情链接</a></li>
				</ul>
			</div>
			<div id="footer_contact" class="footer_contact open">
				<div class="footer_contact_header" title="快速联系">快速联系</div>
				<ul id="footer_contact_ul" class="up" style="display: none;">
					<li><a href="https://gitee.com/zTree/zTree_v3" target="_blank">zTree
							@ Gitee</a></li>
					<li><a href="https://github.com/zTree/zTree_v3"
						target="_blank">zTree @ Github</a></li>
					<li><a href="http://my.oschina.net/dyhunter" target="_blank">zTree
							@ 开源中国</a></li>
					<li><a href="http://ztreeapi.iteye.com/" target="_blank">zTree
							@ ITeye</a></li>
					<li><a href="http://tieba.baidu.com/f?kw=zTree"
						target="_blank">zTree 吧</a></li>
					<li><a href="mailto:hunter.z@263.net">Email</a></li>

				</ul>
			</div>
			<div id="footer_download" class="footer_download open">
				<div class="footer_download_header" title="快速下载">快速下载</div>
				<ul id="footer_download_ul" class="up" style="display: none;">
					<li><a href="https://github.com/zTree/zTree_v3"
						target="_blank">Github 下载</a></li>
					<li><a href="https://gitee.com/zTree/zTree_v3" target="_blank">码云
							下载</a></li>
					<li><a href="https://code.google.com/p/jquerytree/"
						target="_blank">Google 下载</a></li>
				</ul>
			</div>
			<!-- [END] Site Switcher -->

		</div>
	</div>

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>
</body>
</html>