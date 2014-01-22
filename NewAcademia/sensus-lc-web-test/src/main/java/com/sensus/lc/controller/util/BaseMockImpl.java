package com.sensus.lc.controller.util;

import com.sensus.lc.controller.util.enums.ModeEnum;

/**
 * The Class BaseMockImpl.
 */
public abstract class BaseMockImpl
{

	/**
	 * The mode field. Defaults to success mode.
	 */
	private ModeEnum mode = ModeEnum.MODE_SUCCESS;

	/**
	 * Constructs a new Base Mock Object.
	 */
	public BaseMockImpl()
	{
		super();
	}

	/**
	 * Set the response mode.
	 * 
	 * @param modeIn the new mode
	 */
	public void setMode(ModeEnum modeIn)
	{
		mode = modeIn;
	}

	/**
	 * Get the response mode.
	 * 
	 * @return the response mode
	 */
	public ModeEnum getMode()
	{
		return mode;
	}

}