package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Regime;
import com.qat.framework.model.request.MaintenanceRequest;

public class RegimeMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Regime regime;

	/**
	 * The Constructor.
	 */
	public RegimeMaintenanceRequest()
	{

	}

	/**
	 * @return the regime
	 */
	public Regime getRegime()
	{
		return regime;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(Regime regime)
	{
		this.regime = regime;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RegimeMaintenanceRequest [getRegime()=" + getRegime() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}