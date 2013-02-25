package com.sensus.mlc.wui.base.action;

import com.sensus.mlc.wui.base.model.UserSettings;
import com.sensus.mlc.wui.base.util.LocalizationUtil;

/**
 * Base Action for all actions injecting web application settings into JavaScript via FreeMarker templates. This action
 * contains settings common to all pages.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
@SuppressWarnings("serial")
public class SettingsAction extends ActionBase
{

	/**
	 * The maximum resolution for any map.
	 */
	private String maxResolution;

	/**
	 * The maximum extend for any map (value 1).
	 */
	private String maxExtent1;

	/**
	 * The maximum extend for any map (value 2).
	 */
	private String maxExtent2;

	/**
	 * The maximum extend for any map (value 3).
	 */
	private String maxExtent3;

	/**
	 * The maximum extend for any map (value 4).
	 */
	private String maxExtent4;

	/**
	 * The current main menu section. This value is set via the struts configuration and is used by the SiteMesh
	 * decorator to highlight the correct menu section.
	 */
	private String menuItem;

	/**
	 * Load common settings into the response parameter.
	 */
	public void initSettings()
	{
		setMaxResolution(getSettings().getProperty("mlc.map.resolution.max"));
		setMaxExtent1(getSettings().getProperty("mlc.map.extend.max.1"));
		setMaxExtent2(getSettings().getProperty("mlc.map.extend.max.2"));
		setMaxExtent3(getSettings().getProperty("mlc.map.extend.max.3"));
		setMaxExtent4(getSettings().getProperty("mlc.map.extend.max.4"));
	}

	/**
	 * Set the current menu item.
	 * 
	 * @param menuItemIn the current menu item
	 */
	public void setMenuItem(String menuItemIn)
	{
		menuItem = menuItemIn;
	}

	/**
	 * Get the current menu item.
	 * 
	 * @return the current menu item
	 */
	public String getMenuItem()
	{
		return menuItem;
	}

	/**
	 * Get the user's locale settings based on the user's session and the application's defaults.
	 * 
	 * @return the userLocaleSettings
	 */
	public UserSettings getUserLocaleSettings()
	{
		return LocalizationUtil.getUserLocaleSettings(getServletRequest());
	}

	/**
	 * Get the application root context.
	 * 
	 * @return the application root context
	 */
	public String getApplicationRoot()
	{
		return getServletRequest().getContextPath();
	}


	/**
	 * Get the maximum map resolution value.
	 * 
	 * @return the maximum map resolution value
	 */
	public String getMaxResolution()
	{
		return maxResolution;
	}

	/**
	 * Set the maximum map resolution value.
	 * 
	 * @param maxResolutionIn the maximum map resolution value
	 */
	public void setMaxResolution(String maxResolutionIn)
	{
		maxResolution = maxResolutionIn;
	}

	/**
	 * Get the maximum extend (value 1).
	 * 
	 * @return the maximum extend (value 1)
	 */
	public String getMaxExtent1()
	{
		return maxExtent1;
	}

	/**
	 * Set the maximum extend (value 1).
	 * 
	 * @param maxExtend1In the maximum extend (value 1)
	 */
	public void setMaxExtent1(String maxExtend1In)
	{
		maxExtent1 = maxExtend1In;
	}

	/**
	 * Get the maximum extend (value 2).
	 * 
	 * @return the maximum extend (value 2)
	 */
	public String getMaxExtent2()
	{
		return maxExtent2;
	}

	/**
	 * Set the maximum extend (value 2).
	 * 
	 * @param maxExtend2In the maximum extend (value 2)
	 */
	public void setMaxExtent2(String maxExtend2In)
	{
		maxExtent2 = maxExtend2In;
	}

	/**
	 * Get the maximum extend (value 3).
	 * 
	 * @return the maximum extend (value 3)
	 */
	public String getMaxExtent3()
	{
		return maxExtent3;
	}

	/**
	 * Set the maximum extend (value 3).
	 * 
	 * @param maxExtend3In the maximum extend (value 3)
	 */
	public void setMaxExtent3(String maxExtend3In)
	{
		maxExtent3 = maxExtend3In;
	}

	/**
	 * Get the maximum extend (value 4).
	 * 
	 * @return the maximum extend (value 4)
	 */
	public String getMaxExtent4()
	{
		return maxExtent4;
	}

	/**
	 * Set the maximum extend (value 4).
	 * 
	 * @param maxExtend4In the maximum extend (value 4)
	 */
	public void setMaxExtent4(String maxExtend4In)
	{
		maxExtent4 = maxExtend4In;
	}


}
