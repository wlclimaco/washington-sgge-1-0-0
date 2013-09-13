package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class HanAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class HanAction extends DMAction
{
	/** The timeout. */
	private Integer timeout;

	/**
	 * Instantiates a new han action.
	 */
	public HanAction()
	{
	}

	/**
	 * Instantiates a new han action.
	 * 
	 * @param actionType the action type
	 */
	public HanAction(ActionType actionType)
	{
		super(actionType);
	}

	/**
	 * Gets the timeout.
	 * 
	 * @return the timeout
	 */
	public Integer getTimeout()
	{
		return timeout;
	}

	/**
	 * Sets the timeout.
	 * 
	 * @param timeout the new timeout
	 */
	public void setTimeout(Integer timeout)
	{
		this.timeout = timeout;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.action.model.DMAction#toString()
	 */
	@Override
	public String toString()
	{
		return "HanAction [getTimeout()=" + getTimeout() + ", toString()=" + super.toString() + "]";
	}
}
