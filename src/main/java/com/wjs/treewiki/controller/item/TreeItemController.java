package com.wjs.treewiki.controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjs.common.web.BaseController;
import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.service.node.TreeContentService;
import com.wjs.treewiki.service.node.TreeItemService;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.auth.LogonInfo;
import com.wjs.treewiki.vo.item.TreeItemVo;

@Controller
@RequestMapping(value = "/item")
public class TreeItemController extends BaseController{
	
	@Autowired
	TreeItemService treeItemService;
	
	@Autowired
	TreeContentService treeContentService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/add.json")
	@ResponseBody
	public Object add(HttpServletRequest request, TreeItem treeItem){
		
		Long id = null;
		try {
			if(treeItem.getpId() == null){
				throw new Exception("未传入pId");
			}
			id = treeItemService.add(treeItem);

			return success(id);
		} catch (Exception e) {
			return error("新增失败，请重试-->" + e.getMessage());
		}
		
		
	}
	
	

	
	
	@RequestMapping("/del.json")
	@ResponseBody
	public Object del(HttpServletRequest request, Long id){
		
		if(id == null){
			return error("id 为空");
		}
		treeItemService.delRecurItemById(id);
		return success();
	}
	

	@RequestMapping("/move.json")
	@ResponseBody
	public Object move(HttpServletRequest request, Long curId, Long targetId, String moveType){
		
		if(curId == null){
			return error("id 为空");
		}
		treeItemService.move(curId , targetId , moveType);
		return success();
	}
	
	
	/**
	 * 根据用户登录情况获得用户对应的tree结构
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTreeByLoginUser.json")
	@ResponseBody
	public Object getUserTree(HttpServletRequest request) {
		
		LogonInfo loginInfo = userService.getLogonInfo();

		List<TreeItemVo> list = treeItemService.listTreeItemsByUserId(loginInfo.getId());

		if (CollectionUtils.isEmpty(list)) {
			TreeItemVo vo = new TreeItemVo();
			vo.setId(0L);
			vo.setpId(0L);
			vo.setName("无菜单");
			list.add(vo);
		}

		return success(list);
	}
	
	
	
	
	
	
	
	
	
	
	
}
