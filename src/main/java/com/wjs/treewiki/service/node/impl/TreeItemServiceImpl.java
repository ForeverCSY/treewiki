package com.wjs.treewiki.service.node.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.dao.node.TreeContentMapper;
import com.wjs.treewiki.dao.node.TreeItemMapper;
import com.wjs.treewiki.model.node.TreeContentCriteria;
import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.model.node.TreeItemCriteria;
import com.wjs.treewiki.service.node.TreeItemService;
import com.wjs.treewiki.vo.TreeItemVo;

@Service("treeItemService")
public class TreeItemServiceImpl implements TreeItemService{
	
	
	@Autowired
	private TreeItemMapper treeItemMapper;
	
	@Autowired
	private TreeContentMapper treeContentMapper;

	@Override
	public List<TreeItemVo> listTreeItems() {
		
		List<TreeItemVo> list = new ArrayList<>();
		
		TreeItemCriteria crt = new TreeItemCriteria();
		crt.setOrderByClause(" sort , modify_datetime ");
		
		List<TreeItem> items = treeItemMapper.selectByExample(crt);
		if(CollectionUtils.isNotEmpty(items)){
			for (TreeItem treeItem : items) {
				TreeItemVo vo = new TreeItemVo();
				try {
					PropertyUtils.copyProperties(vo, treeItem);
					list.add(vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}

	@Override
	@Transactional
	public Long add(TreeItem treeItem) {
		
		if(treeItem.getSort() == null){
			treeItem.setSort(getFinalChildSort(treeItem.getpId()) + 1);
		}
		treeItem.setCreateDatetime(System.currentTimeMillis());
		treeItem.setModifyDatetime(System.currentTimeMillis());
		
		treeItemMapper.insertSelective(treeItem);
		
		return treeItemMapper.lastInsertId();
	}

	private Integer getFinalChildSort(Long pId) {


		TreeItemCriteria crt = new TreeItemCriteria();
		crt.setStart(0);
		crt.setLimit(1);
		crt.setOrderByClause(" sort desc ");
		crt.createCriteria().andPIdEqualTo(pId);
		List<TreeItem> list = treeItemMapper.selectByExample(crt);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0).getSort();
		}else{
			return 1;
		}
		
	}

	@Override
	@Transactional
	public void delRecurItemById(Long id) {
		
		// 删除子节点
		TreeItemCriteria crt = new TreeItemCriteria();
		crt.createCriteria().andPIdEqualTo(id);
		List<TreeItem> children = treeItemMapper.selectByExample(crt);
		if(CollectionUtils.isNotEmpty(children)){
			for (TreeItem treeItem : children) {
				delRecurItemById(treeItem.getId());
			}
		}
		// 删除自己
		treeItemMapper.deleteByPrimaryKey(id);
		
		// 删除内容
		TreeContentCriteria crtTc = new TreeContentCriteria();
		crtTc.createCriteria().andItemIdEqualTo(id);
		treeContentMapper.deleteByExample(crtTc);
		
	}

	@Override
	public void updateById(TreeItem treeItem) {


		treeItemMapper.updateByPrimaryKeySelective(treeItem);
		
	}

	@Override
	public void move( Long curId, Long targetId, String moveType) {
		
		TreeItem curItem = treeItemMapper.selectByPrimaryKey(curId);
		if(curItem == null){
			throw new RuntimeException("未知异常，节点不存在,ID:" + curId);
		}
		curItem.setModifyDatetime(System.currentTimeMillis());		

		if(Dictionary.ItemMove.INNER.equals(moveType)){
			// 拖到目标节点作为子节点最后一个。
			curItem.setpId(targetId);
			curItem.setSort(getFinalChildSort(targetId) + 1);
			
		}else{
			// 拖到目标节点作为兄弟节点
			TreeItem targetItem = treeItemMapper.selectByPrimaryKey(targetId);
			curItem.setpId(targetItem.getpId());
			if(Dictionary.ItemMove.NEXT.equals(moveType)){

				curItem.setSort(targetItem.getSort() + 1);
			}else{
				curItem.setSort(targetItem.getSort() - 1);	
			}
		}
		
		treeItemMapper.updateByPrimaryKeySelective(curItem);
	}

}
