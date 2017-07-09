package com.clps.mms.sys.tree.impl;

import org.apache.commons.lang.StringUtils;
import com.clps.mms.sys.menu.model.Menu;
import com.clps.mms.sys.tree.IMenuToTree;
import com.clps.mms.sys.tree.ITreeVo;

public class LigerUiMenuToTree implements IMenuToTree {

	/**
	 * TODO 菜单实体转tree（可选）.
	 * @see com.clps.mms.sys.tree.IMenuToTree#menuToTree(com.clps.mms.sys.menu.model.Menu)
	 */
	public ITreeVo menuToTree(Menu menu) {
		ITreeVo tree = new LigerUiTree();

		tree.setId(String.valueOf(menu.getMnu_id()));
		tree.setName(menu.getMnu_name());
		if (!StringUtils.isEmpty(menu.getMnu_url())) {
			tree.setUrl(menu.getMnu_url());
		}
		if (!StringUtils.isEmpty(menu.getMnu_logo_url())) {
			tree.setPic(menu.getMnu_logo_url());
		}

		// 递归遍历menu
		if (menu.getChildren() != null && menu.getChildren().size() > 0) {
			// 遍历每个子menu的children
			for (Menu child : menu.getChildren()) {
				ITreeVo childTree = menuToTree(child);
				tree.addSubTree(childTree); // 组织好数据
			}
		}
		return tree;
	}
}
