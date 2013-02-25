package com.sensus.mlc.wui.group.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;

/**
 * Action rendering Group-related pages. At this point, this page has no extra logic over what LayoutBase already
 * provides.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class GroupPageAction extends LayoutBase
{

	/** CONSTANTS **/

	/** The Constant DELETE_GROUP. */
	private static final String DELETE_GROUP = "group.actions.deleteGroup";

	/**
	 * Gets the action list.
	 * 
	 * @return the action list
	 */
	public Map<String, List<IdValuePair>> getActionList()
	{
		Map<String, List<IdValuePair>> map = new LinkedHashMap<String, List<IdValuePair>>();

		// Delete Group
		List<IdValuePair> deleteGroupList = new ArrayList<IdValuePair>();
		deleteGroupList.add(new IdValuePair("deleteGroup", getText(DELETE_GROUP)));

		map.put(getText(DELETE_GROUP), deleteGroupList);
		return map;

	}
}
