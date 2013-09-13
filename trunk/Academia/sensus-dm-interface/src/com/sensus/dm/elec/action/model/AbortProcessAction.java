package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.DMAction;

/**
 * * The Model Object Action.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class AbortProcessAction extends DMAction
{

	public static final String ACTION = "sensus.epm.action.abort.process";

	/**
	 * Checks if is process required.
	 * 
	 * @return the boolean
	 */
	@Override
	public Boolean isProcessRequired()
	{
		return false;
	}

}