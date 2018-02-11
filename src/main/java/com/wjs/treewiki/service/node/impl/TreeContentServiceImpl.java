package com.wjs.treewiki.service.node.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjs.treewiki.dao.node.TreeContentMapper;
import com.wjs.treewiki.model.node.TreeContent;
import com.wjs.treewiki.model.node.TreeContentCriteria;
import com.wjs.treewiki.service.node.TreeContentService;

@Service("treeContentService")
public class TreeContentServiceImpl implements TreeContentService {

	@Autowired
	TreeContentMapper treeContentMapper;
	
	@Override
	public void addOrUpdateByItemId(TreeContent treeContent) {
		
		TreeContentCriteria crt = new TreeContentCriteria();
		crt.createCriteria().andItemIdEqualTo(treeContent.getItemId());
		List<TreeContent> list = treeContentMapper.selectByExample(crt);
		if(CollectionUtils.isEmpty(list)){
			// 新增
			treeContentMapper.insertSelective(treeContent);
		}else{
			// 更新
			TreeContent oriContent = list.get(0);
			oriContent.setContent(treeContent.getContent());
			treeContentMapper.updateByPrimaryKeyWithBLOBs(oriContent);
		}
		
		
	}

	@Override
	public TreeContent getByItemId(Long itemId) {
		
		TreeContentCriteria crt = new TreeContentCriteria();
		crt.createCriteria().andItemIdEqualTo(itemId);
		List<TreeContent> list = treeContentMapper.selectByExampleWithBLOBs(crt);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		
		return null;
	}

}
