package com.wjs.treewiki.service.node;

import java.util.List;

import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.vo.TreeItemVo;

public interface TreeItemService {

	/**
	 * 查询所有节点
	 * @return
	 */
	List<TreeItemVo> listTreeItems();

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

}
