package com.wjs.treewiki.controller.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjs.common.web.BaseController;
import com.wjs.treewiki.service.node.TreeItemService;
import com.wjs.treewiki.vo.TreeItemVo;

@Controller
public class IndexController extends BaseController {

	@Autowired
	TreeItemService treeItemService;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {

		// request.setAttribute("users", userService.listUser());

		return "index";
	}

	@RequestMapping("/index/getTree.json")
	@ResponseBody
	public Object getTree(HttpServletRequest request) {

		List<TreeItemVo> list = treeItemService.listTreeItems();

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
