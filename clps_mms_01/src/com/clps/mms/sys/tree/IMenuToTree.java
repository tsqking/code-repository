package com.clps.mms.sys.tree;

import com.clps.mms.sys.menu.model.Menu;

public interface IMenuToTree {
	
	/**
	 * menuToTree:(菜单实体转tree). <br/>
	 * @author Charles
	 * @param menu
	 * @return
	 * @since JDK 1.8
	 */
	public ITreeVo menuToTree(Menu menu);

}
