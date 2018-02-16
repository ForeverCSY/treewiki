<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>授权</title>
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

<jsp:include page="./common/static_resource.jsp" />

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babyFirst.js"></script>
<script type="text/javascript">
	var lang = "cn";
</script>

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/adminlte/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/common.alert.1.js"></script>

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.core.js"></script>



<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.excheck.js"></script>

</head>
<body>

	<jsp:include page="./common/head.jsp" />


	<div id="content_wrap" class="content_wrap">
		<div id="content" class="content">
			<div class="nav_section">
				<ul>
				</ul>
			</div>

			<div class="siteTag tag_donate" alt=""></div>
			<center>
				<div id="contentBox" style="width: 800px"
					class="contentBox round clearfix">
					<div id="demoContent" class="demoContent">
						<div class="zTreeDemoBackground left">
							<label>选择用户</label>
							<input id="citySel"
									type="text" readonly value="" style="width: 120px;" /> &nbsp;<a
									id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
						</div>


						<div id="demo_viewer" class="demo_viewer left">
							<label>选择节点</label>
							<ul id="menuTree" class="ztree">
							</ul>


						</div>
						<div id="btn" class="left">
							<input type="button" value=" 保 存  " onclick="auth();"/>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</center>
			<div class="clear"></div>
		</div>
	</div>
	<script type="text/javascript">
		var menu_nodes = [{id:1, pId:0, name:"请选择用户", open:false}];
		$(document).ready(function(){
			demoContent._init();
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
						check: {
							enable: true
						},
						data : {
							simpleData : {
								enable : true,
								rootPId : 0
							}
						},
						check :{
							enable: true,
							chkboxType : { "Y" : "ps", "N" : "ps"},
						},
						callback : {
							onNodeCreated : this.onNodeCreated,
							//beforeClick : this.beforeClick,
							//onClick : this.onClick
						}
					};
					demoContent.zTree_Menu = $.fn.zTree.init($("#menuTree"), $.fn.zTree._z.tools.clone(setting), menu_nodes);
					this.bindEvent();
					// demoContent.showContent()
					
					
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
								
								ComAlert.show(2, alert("报错啦！" + result.exception));
							} else {
								if(result.data != ""){

									demoContent.demoIframe.html(result.data.content);	
								}
							}
						},
						error : function(e) {

							ComAlert.show(2, "未知错误:http状态 >" + e.status);
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



	<script type="text/javascript">
		var user_nodes = [{id:1, pId:0, name:"加载中……", open:false}];
		$.ajax({
			url : '/user/listNormalUser.json',
			// data : params,
			type : "POST",
			cache : false,
			success : function(result) {
				if (result.exception) {
					ComAlert.show(2, "报错啦！" + result.exception);
				} else {
					user_nodes = result.data;
					userContent._init();
				}
			},
			error : function(e) {
				ComAlert.show(2, "未知错误:http状态 >" + e.status);
			}
		});
		
		
		
		
		var userContent = {
				zTree_Menu : null,
				curMenu : null,
				curDemo : null,
				demoIframe : null,
				_init : function() {
					this.demoIframe = $("#demo_viewer");
					var setting = {
							view: {
								selectedMulti : false,
								dblClickExpand: false
							},
							data: {
								simpleData: {
									enable: true
								}
							},
							callback: {
								beforeClick: this.beforeClick,
								onClick: this.onClick
							}
						};
					demoContent.zTree_Menu = $.fn.zTree.init($("#userTree"), $.fn.zTree._z.tools.clone(setting), user_nodes);
				},
				
				
				beforeClick : function(treeId, treeNode) {
					var check = (treeNode && !treeNode.isParent);
					if (!check) alert("请选择...");
					return check;
				},
				onClick : function(e, treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("userTree"),
					nodes = zTree.getSelectedNodes(),
					id = "",
					name = "";
					nodes.sort(function compare(a,b){return a.id-b.id;});
					if(nodes.length < 1){
						return;
					}

					id =  nodes[0].id ;
					name =  nodes[0].name ;
					// 允许多选的情况
					//for (var i=0, l=nodes.length; i<l; i++) {
					//	name += nodes[i].name + ",";
					//	id += nodes[i].id + ",";
					//}
					//if (name.length > 0 ) name = name.substring(0, name.length-1);
					//if (id.length > 0 ) id = id.substring(0, id.length-1);
					
					$.ajax({
						url : '/auth/getTreeByUserId.json',
						data : {userId : id},
						type : "POST",
						cache : false,
						success : function(result) {
							if (result.exception) {
								ComAlert.show(2, "报错啦！" + result.exception);
							} else {
								menu_nodes = result.data;
								demoContent._init();
							}
						},
						error : function(e) {
							ComAlert.show(2, "未知错误:http状态 >" + e.status);
						}
					});
					var cityObj = $("#citySel");
					cityObj.attr("value", name);
					
				}
			};
		
		
		function showMenu() {
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		
		function auth(){
			
			// var nodes = treeObj.getSelectedNodes();
			var userId , nodeIds="";
			
			var userNodes = $.fn.zTree.getZTreeObj("userTree").getSelectedNodes();
			
			if(userNodes.length < 1){
				ComAlert.show(2, "请选择用户");
				return;
			}
			userId = userNodes[0].id;
			
			
			var menuNodes = $.fn.zTree.getZTreeObj("menuTree").getCheckedNodes(true);
			for (var i=0, l=menuNodes.length; i<l; i++) {
				nodeIds += menuNodes[i].id + ",";
			}
			if (nodeIds.length > 0 ) {
				nodeIds = nodeIds.substring(0, nodeIds.length-1)
			};
			
			$.ajax({
				url : '/auth/updateUserAuths.json',
				data : {
					userId : userId,
					nodeIds : nodeIds,
				},
				type : "POST",
				cache : false,
				success : function(result) {
					if (result.exception) {
						ComAlert.show(2, "报错啦！" + result.exception);
					} else {
						ComAlert.show(1, "权限修改完成");
					}
				},
				error : function(e) {
					ComAlert.show(2, "未知错误:http状态 >" + e.status);
				}
			});
		}

	</script>

	<jsp:include page="./common/foot.jsp" />


	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>



	<div id="menuContent" class="menuContent"
		style="display: none; position: absolute;">
		<ul id="userTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
	</div>

</body>
</html>