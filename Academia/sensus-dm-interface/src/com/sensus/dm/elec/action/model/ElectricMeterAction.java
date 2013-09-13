package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.MeterAction;

/**
 * The Class ElectricMeterAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class ElectricMeterAction extends MeterAction
{
	/** The Constant ACTION. */
	public static final String ACTION = "sensus.epm.action.meter.electric";

	/**
	 * Instantiates a new electric meter action.
	 */
	public ElectricMeterAction()
	{
	}

	/**
	 * Instantiates a new electric meter action.
	 * 
	 * @param actionType the action type
	 */
	public ElectricMeterAction(ActionType actionType)
	{
		super(actionType);
	}

}
