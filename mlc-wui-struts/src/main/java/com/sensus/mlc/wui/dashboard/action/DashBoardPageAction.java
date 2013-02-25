package com.sensus.mlc.wui.dashboard.action;

import com.sensus.mlc.wui.base.action.LayoutBase;

/**
 * Action rendering DashBoard-related pages. At this point, this page has no extra logic over what LayoutBase already
 * provides.
 * 
 * @author Raphael Constantino
 * 
 */
@SuppressWarnings("serial")
public class DashBoardPageAction extends LayoutBase
{
	/**
	 * Load page data.
	 * 
	 * @return the string
	 */
	public String loadPageData()
	{
		return SUCCESS;
	}

}
