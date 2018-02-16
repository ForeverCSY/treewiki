package com.wjs.treewiki.service.node.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wjs.common.util.PropertyUtils;
import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.dao.auth.UserItemMapper;
import com.wjs.treewiki.dao.auth.UserMapper;
import com.wjs.treewiki.dao.node.TreeContentMapper;
import com.wjs.treewiki.dao.node.TreeItemMapper;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.model.auth.UserItem;
import com.wjs.treewiki.model.auth.UserItemCriteria;
import com.wjs.treewiki.model.node.TreeContentCriteria;
import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.model.node.TreeItemCriteria;
import com.wjs.treewiki.service.node.TreeItemService;
import com.wjs.treewiki.vo.item.TreeItemVo;

@Service("treeItemService")
public class TreeItemServiceImpl implements TreeItemService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserItemMapper userItemMapper;

	@Autowired
	private TreeItemMapper treeItemMapper;

	@Autowired
	private TreeContentMapper treeContentMapper;

	@Override
	public List<TreeItemVo> listAllTreeItems() {

		List<TreeItemVo> list = new ArrayList<>();

		TreeItemCriteria crt = new TreeItemCriteria();
		crt.setOrderByClause(" sort , modify_datetime ");

		List<TreeItem> items = treeItemMapper.selectByExample(crt);
		convertItemVo(list, items);

		return list;
	}

	private void convertItemVo(List<TreeItemVo> list, List<TreeItem> items) {
		if (CollectionUtils.isNotEmpty(items)) {
			for (TreeItem treeItem : items) {
				TreeItemVo vo = new TreeItemVo();
				PropertyUtils.copyProperties(vo, treeItem);
				list.add(vo);
			}
		}
	}

	@Override
	@Transactional
	public Long add(TreeItem treeItem) {

		if (treeItem.getSort() == null) {
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
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0).getSort();
		} else {
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
		if (CollectionUtils.isNotEmpty(children)) {
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
	public void move(Long curId, Long targetId, String moveType) {

		TreeItem curItem = treeItemMapper.selectByPrimaryKey(curId);
		if (curItem == null) {
			throw new RuntimeException("未知异常，节点不存在,ID:" + curId);
		}
		curItem.setModifyDatetime(System.currentTimeMillis());

		if (Dictionary.ItemMove.INNER.equals(moveType)) {
			// 拖到目标节点作为子节点最后一个。
			curItem.setpId(targetId);
			curItem.setSort(getFinalChildSort(targetId) + 1);

		} else {
			// 拖到目标节点作为兄弟节点
			TreeItem targetItem = treeItemMapper.selectByPrimaryKey(targetId);
			curItem.setpId(targetItem.getpId());
			if (Dictionary.ItemMove.NEXT.equals(moveType)) {

				curItem.setSort(targetItem.getSort() + 1);
			} else {
				curItem.setSort(targetItem.getSort() - 1);
			}
		}

		treeItemMapper.updateByPrimaryKeySelective(curItem);
	}

	@Override
	public List<TreeItemVo> listTreeItemsByUserId(Long userId) {

		List<TreeItemVo> list = new ArrayList<>();
		
		User user = userMapper.selectByPrimaryKey(userId);
		if(user == null){
			throw new RuntimeException("未查询到用户信息,userId:" + userId);
		}
		if(Dictionary.UserType.ADMIN.equals(user.getUserType())){
			// 管理员可以查询所有节点
			list = listAllTreeItems();
		}else if(Dictionary.UserType.NORMAL.equals(user.getUserType())){
			// 普通用户按权限查询节点
			UserItemCriteria mapCrt = new UserItemCriteria();
			mapCrt.createCriteria().andUserIdEqualTo(userId);
			List<UserItem> maps = userItemMapper.selectByExample(mapCrt);
			List<Long> itemIds = PropertyUtils.fetchTypedFieldList(maps, "itemId", Long.class);
			
			
			if(CollectionUtils.isNotEmpty(itemIds)){
				TreeItemCriteria itemCrt = new TreeItemCriteria();
				itemCrt.createCriteria().andIdIn(itemIds);	
				itemCrt.setOrderByClause(" sort , modify_datetime ");
				
				convertItemVo(list, treeItemMapper.selectByExample(itemCrt));
			}
			
			
		}else{
			// 游客用户无权限节点
			// DoNothing
		}
			
		
		return list;
	}

	@Override
	@Transactional
	public void updateUserAuths(Long userId, List<Long> ndIds) {

		// 先删除后增加
		UserItemCriteria crt = new UserItemCriteria();
		crt.createCriteria().andUserIdEqualTo(userId);
		userItemMapper.deleteByExample(crt);
		if(CollectionUtils.isNotEmpty(ndIds)){
			for (Long itemId : ndIds) {
				UserItem userItem = new UserItem();
				userItem.setUserId(userId);
				userItem.setItemId(itemId);
				userItem.setCreateDatetime(System.currentTimeMillis());
				userItemMapper.insertSelective(userItem);
			}
		}
	}

}
