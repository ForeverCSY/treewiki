package com.wjs.treewiki.service.node;

import com.wjs.treewiki.model.node.TreeContent;

public interface TreeContentService {

	void addOrUpdateByItemId(TreeContent treeContent);

	TreeContent getByItemId(Long itemId);

	void addLock(Long id);

	void releaseLock(Long id);

}
