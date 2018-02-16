package com.wjs.treewiki.controller.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjs.common.web.BaseController;
import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.model.node.TreeContent;
import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.service.node.TreeContentService;
import com.wjs.treewiki.service.node.TreeItemService;
import com.wjs.treewiki.vo.item.TreeItemVo;

@Controller
@RequestMapping(value = "/manage")
public class TreeManageController extends BaseController{
	
	@Autowired
	TreeItemService treeItemService;
	
	@Autowired
	TreeContentService treeContentService;
	
	
	
}
