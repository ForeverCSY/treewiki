package com.wjs.treewiki.controller.content;

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
import com.wjs.treewiki.vo.TreeItemVo;

@Controller
@RequestMapping(value = "/content")
public class TreeContentController extends BaseController{
	
	@Autowired
	TreeItemService treeItemService;
	
	@Autowired
	TreeContentService treeContentService;
	
	
	@RequestMapping("/getByItemId.json")
	@ResponseBody
	public Object getByItemId(HttpServletRequest request, Long itemId){
		
		if(itemId == null){
			return error("itemId 为空");
		}
		TreeContent treeContent = treeContentService.getByItemId(itemId);
		return success(treeContent == null ? "" : treeContent.getContent());
	}
	

	
	@RequestMapping("/edit.json")
	@ResponseBody
	@Transactional
	public Object edit(HttpServletRequest request, Long id, String name, String content){
		
		if(id == null){
			return error("id 为空");
		}
		TreeItem treeItem = new TreeItem();
		treeItem.setId(id);
		treeItem.setName(name);
		treeItemService.updateById(treeItem);
		
		TreeContent treeContent = new TreeContent();
		treeContent.setItemId(id);
		treeContent.setContent(content);
		treeContent.setCreateDatetime(System.currentTimeMillis());
		treeContent.setLockby(Dictionary.CommonYesNo.NO);
		treeContent.setLockbyId(0L);
		treeContent.setLockbyName("");
		treeContentService.addOrUpdateByItemId(treeContent);
		
		return success();
	}
	
}
