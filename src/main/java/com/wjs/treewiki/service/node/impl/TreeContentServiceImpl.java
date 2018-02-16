package com.wjs.treewiki.service.node.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.dao.node.TreeContentMapper;
import com.wjs.treewiki.model.node.TreeContent;
import com.wjs.treewiki.model.node.TreeContentCriteria;
import com.wjs.treewiki.service.node.TreeContentService;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.auth.LogonInfo;

@Service("treeContentService")
public class TreeContentServiceImpl implements TreeContentService {

	@Autowired
	TreeContentMapper treeContentMapper;
	
	@Autowired
	UserService userService;
	
	@Override
	public void addOrUpdateByItemId(TreeContent treeContent) {
		
		TreeContentCriteria crt = new TreeContentCriteria();
		crt.createCriteria().andItemIdEqualTo(treeContent.getItemId());
		List<TreeContent> list = treeContentMapper.selectByExample(crt);
		if(CollectionUtils.isEmpty(list)){
			// 新增
			treeContent.setLockby(Dictionary.CommonYesNo.NO);
			treeContent.setLockbyId(0L);
			treeContent.setLockbyName("");
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

	@Override
	public void addLock(Long id) {
		
		LogonInfo logInfo =  userService.getLogonInfo();
		TreeContentCriteria crt = new TreeContentCriteria();
		crt.createCriteria().andItemIdEqualTo(id);
		List<TreeContent> treeContents = treeContentMapper.selectByExample(crt);
		if(CollectionUtils.isEmpty(treeContents)){
			return;
		}
		TreeContent treeContent = treeContents.get(0);
		
		if(Dictionary.CommonYesNo.YES.equals(treeContent.getLockby()) && !treeContent.getLockbyId().equals(logInfo.getId())){
			throw new RuntimeException("节点已被["+ treeContent.getLockbyName() +"]加锁");
		}
		treeContent.setLockby(Dictionary.CommonYesNo.YES);
		treeContent.setLockbyId(logInfo.getId());
		treeContent.setLockbyName(logInfo.getLoginName());
		treeContentMapper.updateByPrimaryKeySelective(treeContent);
	}

	@Override
	public void releaseLock(Long id) {

		LogonInfo logInfo =  userService.getLogonInfo();
		TreeContentCriteria crt = new TreeContentCriteria();
		crt.createCriteria().andItemIdEqualTo(id);
		List<TreeContent> treeContents = treeContentMapper.selectByExample(crt);
		if(CollectionUtils.isEmpty(treeContents)){
			return;
		}
		TreeContent treeContent = treeContents.get(0);
		if(!logInfo.getId().equals(treeContent.getLockbyId())){
			throw new RuntimeException("节点需由加锁人["+ treeContent.getLockbyName() +"]解锁");
		}
		treeContent.setLockby(Dictionary.CommonYesNo.NO);
		treeContent.setLockbyId(0L);
		treeContent.setLockbyName("");
		treeContentMapper.updateByPrimaryKeySelective(treeContent);
		
	}

}
