package com.sensus.mlc.wui.schedule.action;

import java.util.ArrayList;
import java.util.List;

import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;

/**
 * Action rendering the main schedule-related pages. At this point, this page has no extra logic over what LayoutBase
 * already
 * provides.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class SchedulePageAction extends LayoutBase
{

	/** The Constant DELETE_SECHEDULE_KEY. */
	private static final String DELETE_SECHEDULE_KEY = "deleteSchedule";

	/**
	 * Gets the action list.
	 * 
	 * @return the action list
	 */
	public List<IdValuePair> getActionList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		list.add(new IdValuePair(DELETE_SECHEDULE_KEY, getText("schedule.actions.deleteSchedule")));
		return list;
	}
}
