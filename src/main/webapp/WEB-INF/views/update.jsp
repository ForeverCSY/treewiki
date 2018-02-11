<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
var basePath = "<%=request.getAttribute("basePath")%>";
	console.log("head.jsp-->basepath:" + basePath);
</script>

<meta name="description" content="treewiki_content">
<meta name="keywords"
	content="jQuery, javascript, Tree, plugIn, 树, 插件, 树插件, Web, Web前端">
<title>treewiki</title>
<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/css/common.css"
	type="text/css">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="stylesheet"
	href="<%=request.getAttribute("basePath")%>/static/css/zTreeStyle/zTreeStyle.css"
	type="text/css">


<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/babyFirst.js"></script>
<script type="text/javascript">
	var lang = "cn";
</script>
<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery-1.6.2.min.js"></script>

<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.core.js"></script>


<script type="text/javascript"
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.exedit.js"></script>


<script type="text/javascript" charset="utf-8"
	src="<%=request.getAttribute("basePath")%>/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getAttribute("basePath")%>/static/ueditor/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="<%=request.getAttribute("basePath")%>/static/ueditor/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript">
	var zNodes = [ {
		id : 1,
		pId : 0,
		name : "加载中……",
		open : false
	} ];

	$.ajax({
		url : '/index/getTree.json',
		// data : params,
		type : "POST",
		cache : false,
		success : function(result) {
			if (result.exception) {
				alert("报错啦！" + result.exception);
			} else {
				zNodes = result.data;
				resetTree();
			}
		},
		error : function(e) {
			alert("未知错误:http状态 >" + e.status);
		}
	});

	var setting = {
		view : {
			dblClickExpand : true
		},
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false,
			drag : {
				autoExpandTrigger : true,
				isCopy : true,
				isMove : true,
				prev : false,
				next : true,
				inner : true,
			}
		},
		data : {
			simpleData : {
				enable : !0,
				rootPId : ""
			}
		},
		check : {
			enable : true
		},
		callback : {
			onRightClick : OnRightClick,
			onClick : onClick,
			beforeDrag : beforeDrag,
			beforeDrop : beforeDrop
		}
	};

	function OnRightClick(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button"
				&& $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
	}

	function onClick(event, treeId, treeNode) {
		console.log("id:" + treeNode.id + ",pId:" + treeNode.pId + ",name:"
				+ treeNode.name + ",content:" + treeNode.content);

		$.ajax({
			url : '/content/getByItemId.json',
			data : {
				itemId : treeNode.id,
			},
			type : "POST",
			cache : false,
			success : function(result) {
				if (result.exception) {
					alert("报错啦！" + result.exception);
				} else {
					$("#treeId").val(treeNode.id);
					$("#treePId").val(treeNode.pId);
					$("#treeName").val(treeNode.name);
					UE.getEditor('editor').setContent(result.data, false);
				}
			},
			error : function(e) {
				alert("未知错误:http状态 >" + e.status);
			}
		});
	}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type == "root") {
			$("#m_del").hide();
			$("#m_check").hide();
			$("#m_unCheck").hide();
		} else {
			$("#m_del").show();
			$("#m_check").show();
			$("#m_unCheck").show();
		}
		y += document.body.scrollTop;
		x += document.body.scrollLeft;
		rMenu.css({
			"top" : y + "px",
			"left" : x + "px",
			"visibility" : "visible"
		});
		$("body").bind("mousedown", onBodyMouseDown);
	}
	function hideRMenu() {
		if (rMenu)
			rMenu.css({
				"visibility" : "hidden"
			});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event) {
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
			rMenu.css({
				"visibility" : "hidden"
			});
		}
	}
	var addCount = 1;
	function addTreeNode() {
		hideRMenu();

		var nodes = zTree.getSelectedNodes();
		var pId;
		if (nodes && nodes.length > 0) {
			pId = nodes[0].id;
		}
		if (typeof (pId) == "undefined" || pId == null) {
			pId = 0;
		}

		addCount++;
		$.ajax({
			url : '/item/add.json',
			data : {
				name : "增加" + (addCount),
				content : "",
				pId : pId,
			},
			type : "POST",
			cache : false,
			success : function(result) {
				if (result.exception) {
					alert("报错啦！" + result.exception);
				} else {
					var newNode = {
						id : result.data,
						name : "增加" + (addCount),
						content : "",
						pId : pId,
					};
					if (zTree.getSelectedNodes()[0]) {
						newNode.checked = zTree.getSelectedNodes()[0].checked;
						zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
					} else {
						zTree.addNodes(null, newNode);
					}
				}
			},
			error : function(e) {
				alert("未知错误:http状态 >" + e.status);
			}
		});

	}
	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length > 0) {
			if (nodes[0].children && nodes[0].children.length > 0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg) == true) {

					$.ajax({
						url : '/item/del.json',
						data : {
							id : nodes[0].id
						},
						type : "POST",
						cache : false,
						success : function(result) {
							if (result.exception) {
								alert("报错啦！" + result.exception);
							} else {
								zTree.removeNode(nodes[0]);
							}
						},
						error : function(e) {
							alert("未知错误:http状态 >" + e.status);
						}
					});
				}
			} else {
				var msg = "删除操作执行后，无法自动恢复\n\n请确认！";
				if (confirm(msg) == true) {

					$.ajax({
						url : '/item/del.json',
						data : {
							id : nodes[0].id
						},
						type : "POST",
						cache : false,
						success : function(result) {
							if (result.exception) {
								alert("报错啦！" + result.exception);
							} else {
								zTree.removeNode(nodes[0]);
							}
						},
						error : function(e) {
							alert("未知错误:http状态 >" + e.status);
						}
					});
				}
			}
		}
	}

	function beforeDrag(treeId, treeNodes, targetNode, moveType) {
		//for (var i=0,l=treeNodes.length; i<l; i++) {
		//	if (treeNodes[i].drag === false) {
		//		return false;
		//	}
		//}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {

		$("#treeId").val(treeId);
		$("#treePId").val(targetNode.id);
		console.log("curTreeId:" + treeId + ",targetId:" + targetNode.id
				+ "moveType-->" + moveType);

		if (targetNode) {
			for (var i = 0; i < treeNodes.length; i++) {
				$.ajax({
					url : '/item/move.json',
					data : {
						curId : treeNodes[i].id,
						targetId : targetNode.id,
						moveType : moveType
					},
					type : "POST",
					cache : false,
					success : function(result) {
						if (result.exception) {
							alert("报错啦！" + result.exception);
							return false;
						}
					},
					error : function(e) {
						alert("未知错误:http状态 >" + e.status);
					}
				});
			}

		}

		return targetNode ? targetNode.drop !== false : true;
	}

	function save() {
		var id = $("#treeId").val();
		var name = $("#treeName").val();
		var content = UE.getEditor('editor').getContent();
		console.log("id:" + id + "name:" + name + ",content:" + content);

		$.ajax({
			url : '/content/edit.json',
			data : {
				id : id,
				name : name,
				content : content,
			},
			type : "POST",
			cache : false,
			success : function(result) {
				if (result.exception) {
					alert("报错啦！" + result.exception);
				} else {
					alert("保存成功");
				}
			},
			error : function(e) {
				alert("未知错误:http状态 >" + e.status);
			}
		});

		// TODO 保存完毕，自动释放锁，页面只读
	}

	function resetTree() {
		hideRMenu();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	}
	var zTree, rMenu;
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		rMenu = $("#rMenu");

		UE.getEditor('editor');
	});
</script>

<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #555;
	text-align: left;
	padding: 2px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
</style>

</head>
<body>

	<jsp:include page="./common/head.jsp" />

	<div id="content_wrap" class="content_wrap">
		<div id="content" class="content">
			<div class="nav_section">
				<ul>
				</ul>
			</div>

			<div id="contentBox" class="contentBox round clearfix">
				<div id="demoContent" class="demoContent">
					<div id="demo_menu" class="demo_menu left ">
						<ul id="treeDemo" class="ztree">
						</ul>
					</div>
					<div id="demo_viewer" class="demo_viewer right">
						<div class="zTreeDemoBackground left">

							<ul class="info">

								<li><a id="menuBtn" href="#"
									onclick="lock(); return false;">锁定</a> <a id="menuBtn" href="#"
									onclick="save(); return false;">保存</a></li>
								<li><label>todo-->red：已经被xxx加锁</label></li>
							</ul>

							<ul class="info">

								<input type="hidden" id="treeId"></input>
								<li>节点名：<input id="treeName" type="text" value=""
									style="width: 320px;"> &nbsp;
								</li>
							</ul>
						</div>

						<div class="clear"></div>
						<script id="editor" type="text/plain"></script>
					</div>



				</div>

			</div>
			<div class="clear"></div>
		</div>
	</div>

	
	<jsp:include page="./common/foot.jsp" />


	<div id="rMenu">
		<ul>
			<li id="m_add" onclick="addTreeNode();">新增子节点</li>
			<li id="m_del" onclick="removeTreeNode();">删除节点</li>
			<li id="m_reset" onclick="resetTree();">更新目录结构</li>
		</ul>
	</div>

	<script type="text/javascript"
		src="<%=request.getAttribute("basePath")%>/static/js/babygo.js"></script>


</body>
</html>