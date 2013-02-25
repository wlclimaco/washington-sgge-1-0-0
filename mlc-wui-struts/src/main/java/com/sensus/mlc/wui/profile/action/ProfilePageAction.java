package com.sensus.mlc.wui.profile.action;

import java.util.HashMap;
import java.util.Map;

import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.wui.base.action.LayoutBase;

/**
 * The Class ProfilePageAction.
 * 
 * @author Raphael Constantino
 */
@SuppressWarnings("serial")
public class ProfilePageAction extends LayoutBase
{

	/** The lc help. */
	private transient LCHelp lcHelp;

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/** The map time zone. */
	private Map<String, String> mapTimeZone = new HashMap<String, String>();

	/**
	 * Open profile page action.
	 * 
	 * @return the string
	 */

	public String openProfile()
	{

		return SUCCESS;
	}

	/**
	 * Sets the map time zone.
	 * 
	 * @param mapTimeZone the mapTimeZone to set
	 */
	public void setMapTimeZone(Map<String, String> mapTimeZone)
	{
		this.mapTimeZone = mapTimeZone;
	}

	/**
	 * Gets the map time zone.
	 * 
	 * @return the mapTimeZone
	 */
	public Map<String, String> getMapTimeZone()
	{
		return mapTimeZone;
	}
}
