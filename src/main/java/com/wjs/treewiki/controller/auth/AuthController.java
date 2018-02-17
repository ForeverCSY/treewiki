package com.wjs.treewiki.controller.auth;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjs.common.web.BaseController;
import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.constant.WebConstant;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.service.node.TreeItemService;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.auth.LogonInfo;
import com.wjs.treewiki.vo.item.TreeItemVo;
import com.wjs.treewiki.vo.user.UserItemVo;

@RequestMapping(value = "/auth")
@Controller
public class AuthController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	TreeItemService treeItemService;

	/**
	 * 根据用户ID获得用户对应的tree结构
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTreeByUserId.json")
	@ResponseBody
	public Object getTreeByUserId(HttpServletRequest request, String userId) {

		LogonInfo loginInfo = userService.getLogonInfo();

		List<TreeItemVo> allList = treeItemService.listTreeItemsByUserId(loginInfo.getId());
		Long id = -1L;
		if (WebConstant.ALL.equals(userId)) {
			id = loginInfo.getId();
		} else {
			id = Long.valueOf(userId);
			List<TreeItemVo> userList = treeItemService.listTreeItemsByUserId(id);
			setAuth(allList, userList);
		}

		return success(allList);
	}

	private void setAuth(List<TreeItemVo> allList, List<TreeItemVo> userList) {

		for (TreeItemVo treeItemAll : allList) {

			treeItemAll.setOpen(true);
			for (TreeItemVo treeItemUser : userList) {
				if (treeItemAll.getId().equals(treeItemUser.getId())) {
					treeItemAll.setChecked(true);
					treeItemAll.setDrag(true);
					treeItemAll.setDrop(true);
					treeItemAll.setNoR(true);
				}
			}
		}

	}

	/**
	 * 更新用户权限
	 * 
	 * @param request
	 * @param userId
	 * @param nodeIds
	 * @return
	 */
	@RequestMapping("/updateUserAuths.json")
	@ResponseBody
	public Object updateUsersAuths(HttpServletRequest request, String userIds, String nodeIds) {

		List<Long> ndIds = new ArrayList<>();

		String[] ndsArr = nodeIds.split(",");

		for (String str : ndsArr) {
			ndIds.add(Long.valueOf(str));
		}
		
		if (WebConstant.ALL.equals(userIds)) {
			List<User> users = userService.listUsersByType(Dictionary.UserType.NORMAL);
			for (User user : users) {
				addUserAuths(user.getId(), ndIds);
			}
		} else {
			updateUserAuths(Long.valueOf(userIds), ndIds);
		}
		return success();
	}
	
	private void addUserAuths(Long userId, List<Long> ndIds) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new RuntimeException("用户不存在,ID:" + userId);
		}
		if (Dictionary.UserType.ADMIN.equals(user.getUserType())) {

			throw new RuntimeException("管理员权限用户可修改查询所有菜单权限");
		}

		treeItemService.addUserAuths(userId, ndIds);
	}

	private void updateUserAuths(Long userId, List<Long> ndIds) {
		User user = userService.getUserById(userId);
		if (user == null) {
			throw new RuntimeException("用户不存在,ID:" + userId);
		}
		if (Dictionary.UserType.ADMIN.equals(user.getUserType())) {

			throw new RuntimeException("管理员权限用户可修改查询所有菜单权限");
		}

		treeItemService.updateUserAuths(userId, ndIds);
	}

}
