package com.sensus.dm.water.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.MeterAction;

/**
 * The Class ElectricMeterAction.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class WaterMeterAction extends MeterAction
{
	/** The Constant ACTION. */
	public static final String ACTION = "sensus.epm.action.meter.water";

	/**
	 * Instantiates a new water meter action.
	 */
	public WaterMeterAction()
	{
	}

	/**
	 * Instantiates a new water meter action.
	 * 
	 * @param actionType the action type
	 */
	public WaterMeterAction(ActionType actionType)
	{
		super(actionType);
	}

}
