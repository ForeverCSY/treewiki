package com.wjs.treewiki.service.node;

import java.util.List;

import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.vo.item.TreeItemVo;

public interface TreeItemService {

	/**
	 * 查询所有节点
	 * @return
	 */
	List<TreeItemVo> listAllTreeItems();
	
	// 根据用户ID查询有权限的节点
	List<TreeItemVo> listTreeItemsByUserId(Long userId);

	/**
	 * 新增节点，并返回主键ID值
	 * @param treeItem
	 * @return
	 */
	Long add(TreeItem treeItem);

	/**
	 * 按照ID删除节点及子节点(递归)
	 * @param id
	 */
	void delRecurItemById(Long id);

	/**
	 * 按照ID修改节点信息
	 * @param treeItem
	 */
	void updateById(TreeItem treeItem);

	/**
	 * 菜单结构拖拽
	 * @param curTreeId
	 * @param newPid
	 * @param moveType
	 */
	void move(Long curTreeId, Long newPid, String moveType);

	/**
	 * 更新某个用户下的权限
	 * @param userId
	 * @param ndIds
	 */
	void updateUserAuths(Long userId, List<Long> ndIds);

	
	/**
	 * 为某个用户增加权限
	 * @param userId
	 * @param ndIds
	 */
	void addUserAuths(Long userId, List<Long> ndIds);


}
