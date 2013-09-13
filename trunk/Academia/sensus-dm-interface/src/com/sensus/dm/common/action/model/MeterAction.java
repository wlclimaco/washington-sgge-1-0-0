package com.sensus.dm.common.action.model;

/**
 * The Class MeterAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class MeterAction extends DMAction
{
	/** The Constant ACTION. */
	public static final String ACTION = "sensus.epm.action.meter";

	/**
	 * Instantiates a new meter action.
	 */
	public MeterAction()
	{
	}

	/**
	 * Instantiates a new meter action.
	 * 
	 * @param actionType the action type
	 */
	public MeterAction(ActionType actionType)
	{
		super(actionType);
	}

}
