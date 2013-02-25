package com.sensus.mlc.wui.analytics.action;

import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.wui.base.action.LayoutBase;

/**
 * Action rendering Analytics-related pages. At this point, this page has no extra logic over what LayoutBase already
 * provides.
 * 
 * @author Raphael Constantino
 * 
 */

@SuppressWarnings("serial")
public class AnalyticsPageAction extends LayoutBase
{

	/** CONSTANTS **/

	/**
	 * The default "empty" value for droplists.
	 */
	private static final String DEFAULT_VALUE = "0";

	/** The Constant ALL_LIGHTS. */
	private static final String ALL_LIGHTS = "analytics.page.allLights";

	/** The Constant ALL. */
	private static final String ALL = "all";

	/**
	 * The key for the default "loading error" droplist prompt.
	 */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String GROUP_LIST_ERROR = "Error loading Group List";

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/**
	 * Gets the group list.
	 * 
	 * @return the group list
	 */
	/*
	 * public List<IdValuePair> getGroupList()
	 * {
	 * List<IdValuePair> list = new ArrayList<IdValuePair>();
	 * try
	 * {
	 * AnalyticsRequest request = new AnalyticsRequest(getUserContext());
	 * AnalyticsResponse response = getAnalyticsBCF().fetchAllAnalyticsGroup(request);
	 * if (!response.isOperationSuccess())
	 * {
	 * list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * if (LOG.isErrorEnabled())
	 * {
	 * LOG.error(GROUP_LIST_ERROR);
	 * }
	 * }
	 * else
	 * {
	 * if (!ValidationUtil.isNull(response.getAnalyticsGroups()))
	 * {
	 * for (AnalyticsGroup group : response.getAnalyticsGroups())
	 * {
	 * if (group.getName().equalsIgnoreCase(ALL))
	 * {
	 * list.add(new IdValuePair(DEFAULT_VALUE, getText(ALL_LIGHTS)));
	 * }
	 * }
	 * for (AnalyticsGroup group : response.getAnalyticsGroups())
	 * {
	 * if (!group.getName().equalsIgnoreCase(ALL))
	 * {
	 * list.add(new IdValuePair(group.getId(), group.getName()));
	 * }
	 * }
	 * }
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
	 * if (LOG.isErrorEnabled())
	 * {
	 * LOG.error(GROUP_LIST_ERROR, e);
	 * }
	 * }
	 * return list;
	 * }
	 */

	/**
	 * Gets the analytics bcf.
	 * 
	 * @return the analytics bcf
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return analyticsBCF;
	}

	/**
	 * Sets the analytics bcf.
	 * 
	 * @param analyticsBCF the new analytics bcf
	 */
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
	}

}
