<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>文档编辑</title>
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
	src="<%=request.getAttribute("basePath")%>/static/js/jquery.ztree.exedit.js"></script>


<script type="text/javascript" charset="utf-8"
	src="<%=request.getAttribute("basePath")%>/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getAttribute("basePath")%>/static/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="<%=request.getAttribute("basePath")%>/static/ueditor/lang/zh-cn/zh-cn.js"></script>


<script type="text/javascript">
	var menu_nodes = [ {
		id : 1,
		pId : 0,
		name : "加载中……",
		open : false
	} ];
	
	var ue_viewer;
	$(document).ready(function() {

		ue_viewer = UE.getEditor('editor');
		
		ue_viewer.addListener("ready", function () {  
			
			
            // editor准备好之后才可以使用  
			$.ajax({
				url : '/item/getTreeByLoginUser.json',
				// data : params,
				type : "POST",
				cache : false,
				success : function(result) {
					if (result.exception) {
						ComAlert.show(2, "报错啦！" + result.exception);
					} else {
						menu_nodes = result.data;
						demoContent._init();
						rMenu = $("#rMenu");
					}
				},
				error : function(e) {

					ComAlert.show(2, "未知错误:http状态 >" + e.status);
				}
			}); 
            
			// 默认不可编辑，需要加锁后才可以编辑。
			pageDisabled();

    	});  
		
	});

	
	
	

	var rMenu;

	var demoContent = {
			
			zTree : null,
			curMenu : null,
			curDemo : null,
			demoIframe : null,
			_init : function() {
				this.demoIframe = $("#demo_viewer");
				var setting = {
					view : {
						// showIcon : this.showIcon,
						// showLine : true,
						selectedMulti : true,
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
							enable : true,
							rootPId : 0
						}
					},
					check : {
						enable : true
					},
					callback : {
						onNodeCreated : this.onNodeCreated,
						beforeClick : this.beforeClick,
						onClick : this.onClick,
						

						onRightClick : this.OnRightClick,
						// onClick : onClick,
						beforeDrag : this.beforeDrag,
						beforeDrop : this.beforeDrop
					}
				};
				demoContent.zTree = $.fn.zTree.init($("#menuTree"), $.fn.zTree._z.tools.clone(setting), menu_nodes);
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
				a || (!a && c.length > 0 && ( a = demoContent.zTree.getNodeByParam("id", c)), a || ( a = demoContent.zTree.getNodes()[0].children[0]), demoContent.zTree.selectNode(a));
				if (a){
					demoContent.curMenu = a.getParentNode();
					//demoContent.demoIframe.attr("src", "demo/" + lang + "/" + a.content + ".html");
					
					$("#treeId").val(a.id);
					$("#treePId").val(a.pId);
					$("#treeName").val(a.name);
					
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
							ComAlert.show(2, "报错啦！" + result.exception);
							
						} else {
							if(result.data != ""){
								if("1" == result.data.lockby){

									
									if(logonInfo.id == result.data.lockbyId){
										// 区分自己的锁 
										lock.addPageLock(0,"");
									}else{

										// 设置加锁信息
										lock.addPageLock(1,"内容已被["+result.data.lockbyName+"]加锁");
									}
								}else{
									lock.releasePageLock("修改前请加锁");
									
								}
								ue_viewer.setContent(result.data.content, false);
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
//				var a = demoContent.demoIframe.contents().find("body").get(0).scrollHeight, b = demoContent.demoIframe.contents().find("html").get(0).scrollHeight, c = Math.max(a, b), a = Math.min(a, b), c = demoContent.demoIframe.height() >= c ? a : c;
//				c < 530 && ( c = 530);
//				ie ? demoContent.demoIframe.height(c) : demoContent.demoIframe.animate({
//					height : c
//				}, {
//					duration : "normal",
//					easing : "swing",
//					complete : null
//				})
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
					demoContent.zTree.expandNode(node, !0);
					demoContent.zTree.expandNode(demoContent.curMenu, !1);
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
				
				//var rtn = !node.blank && node.level != 0 && !demoContent.zTree.isSelectedNode(node);
				var rtn = !node.blank && !demoContent.zTree.isSelectedNode(node);
				return rtn;
			},
			onClick : function(event, treeId, treeNode) {
				// 内容显示
				demoContent.showContent(treeNode)
			},
			
			

			OnRightClick : function(event, treeId, treeNode) {
				if (!treeNode && event.target.tagName.toLowerCase() != "button"
						&& $(event.target).parents("a").length == 0) {
					demoContent.zTree.cancelSelectedNode();
					showRMenu("root", event.clientX, event.clientY);
				} else if (treeNode && !treeNode.noR) {
					demoContent.zTree.selectNode(treeNode);
					showRMenu("node", event.clientX, event.clientY);
				}
			},
			

			beforeDrag : function(treeId, treeNodes, targetNode, moveType) {
				//for (var i=0,l=treeNodes.length; i<l; i++) {
				//	if (treeNodes[i].drag === false) {
				//		return false;
				//	}
				//}
				return true;
			},
			
			beforeDrop : function(treeId, treeNodes, targetNode, moveType) {

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
									ComAlert.show(2, "报错啦！" + result.exception);
									return false;
								}
							},
							error : function(e) {

								ComAlert.show(2, "未知错误:http状态 >" + e.status);
							}
						});
					}

				}

				return targetNode ? targetNode.drop !== false : true;
			}
			
		};

	var addCount = 1;
	function addTreeNode() {
		hideRMenu();

		var nodes = demoContent.zTree.getSelectedNodes();
		var pId;
		if (nodes && nodes.length > 0) {
			pId = nodes[0].id;
		}
		if (typeof (pId) == "undefined" || pId == null) {
			pId = 0;
		}

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
					ComAlert.show(2, "报错啦！" + result.exception);
				} else {
					var newNode = {
						id : result.data,
						name : "增加" + (addCount),
						content : "",
						pId : pId,
					};
					if (demoContent.zTree.getSelectedNodes()[0]) {
						newNode.checked = demoContent.zTree.getSelectedNodes()[0].checked;
						demoContent.zTree.addNodes(demoContent.zTree.getSelectedNodes()[0], newNode);
					} else {
						demoContent.zTree.addNodes(null, newNode);
					}
				}
			},
			error : function(e) {

				ComAlert.show(2, "未知错误:http状态 >" + e.status);
			}
		});

	}
	

	function removeTreeNode() {
		hideRMenu();
		var nodes = demoContent.zTree.getSelectedNodes();
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
								ComAlert.show(2, "报错啦！" + result.exception);
							} else {
								demoContent.zTree.removeNode(nodes[0]);
							}
						},
						error : function(e) {

							ComAlert.show(2, "未知错误:http状态 >" + e.status);
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
								ComAlert.show(2, "报错啦！" + result.exception);
							} else {
								demoContent.zTree.removeNode(nodes[0]);
							}
						},
						error : function(e) {

							ComAlert.show(2, "未知错误:http状态 >" + e.status);
						}
					});
				}
			}
		}
	}
	
	

	function resetTree() {
		hideRMenu();
		$.ajax({
			url : '/item/getTreeByLoginUser.json',
			// data : params,
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
					ComAlert.show(2, "报错啦！" + result.exception);
				} else {
					ComAlert.show(1, "保存成功");
				}
			},
			error : function(e) {

				ComAlert.show(2, "未知错误:http状态 >" + e.status);
			}
		});

		// 保存完毕，自动释放锁，页面只读:经考虑，用户有不断保存的习惯，不合适怎么做。
		// lock.releaseLock();
	}
	
	function toLock(){
		
		// 查看当前是否加锁，0否1是
		if("1" == $("#lockby").val()){
			// 已经加锁，释放锁
			lock.releaseLock("");
		}else{
			lock.addLock(0,"");
		}
	}
	
	var lock = {
			
			releasePageLock : function (content){
				// 仅作页面调整
				$("#lockby").val("0");
				$("#lockBtn").html("加锁");
				$("#lockLabel").html(content);
				pageDisabled();
			},

			addPageLock : function(lockType,content){
				// lockType,0：自己，1：其他
				
				// 仅作页面调整
				$("#lockby").val("1");
				$("#lockBtn").html("释放锁");
				$("#lockLabel").html(content);
				// lockType=0,自己加锁。lockType=1.别人加的锁
				if(lockType == 0){
					pageEnable();
				}else{
					pageDisabled();
				}
			},
			releaseLock : function (content){
				
				// ajax 释放锁
				$.ajax({
					url : '/content/releaseLock.json',
					data : {
						id : demoContent.zTree.getSelectedNodes()[0].id,
					},
					type : "POST",
					cache : false,
					success : function(result) {
						if (result.exception) {
							ComAlert.show(2, "报错啦！" + result.exception);
						} else {
							ComAlert.show(1, "锁释放成功");
							lock.releasePageLock("");
						}
					},
					error : function(e) {
		
						ComAlert.show(2, "未知错误:http状态 >" + e.status);
					}
				});

				
			},

			addLock : function(lockType,content){
				// lockType,0：自己，1：其他
				// ajax 加锁
				$.ajax({
					url : '/content/addLock.json',
					data : {
						id : demoContent.zTree.getSelectedNodes()[0].id,
					},
					type : "POST",
					cache : false,
					success : function(result) {
						if (result.exception) {
							ComAlert.show(2, "报错啦！" + result.exception);
						} else {
							ComAlert.show(1, "加锁成功");
							lock.addPageLock(0,"");
						}
					},
					error : function(e) {
		
						ComAlert.show(2, "未知错误:http状态 >" + e.status);
					}
				});
			}
		
	};
	
	
	function pageDisabled(){
		$("#treeName").attr("disabled",true);
		ue_viewer.setDisabled();
	}
	function pageEnable(){
		$('#treeName').attr("disabled",false);
		ue_viewer.setEnabled();
	}

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
					<div id="demo_menu" class="demo_menu left">
						<ul id="menuTree" class="ztree">
						</ul>
					</div>
					<div id="demo_viewer" class="demo_viewer right">
						<div class="zTreeDemoBackground left" style="padding: 20px 0;">

							<ul class="info">
								<input id="lockby" type="hidden"/>
								<li>
									<a id="lockBtn" class="btn btn-danger" href="#" onclick="toLock(); return false;">加锁</a> 
									<a id="saveBtn" class="btn btn-primary" href="#" onclick="save(); return false;">保存</a>
								</li>
								<li><label id="lockLabel" style="color:red;">red：已经被xxx加锁</label></li>
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


<script type="text/javascript">
	$('#js_selected_update').addClass('selected')
</script>
</body>
</html>