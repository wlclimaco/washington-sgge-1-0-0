package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Risk;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * The Class RiskMaintenanceRequest.
 */
public class RiskMaintenanceRequest extends MaintenanceRequest
{
	/** Attributes. */
	private Risk risk;

	/**
	 * The Constructor.
	 */
	public RiskMaintenanceRequest()
	{
	}

	/**
	 * Gets the risk.
	 *
	 * @return the risk
	 */
	public Risk getRisk()
	{
		return risk;
	}

	/**
	 * Sets the risk.
	 *
	 * @param risk the risk
	 */
	public void setRisk(Risk risk)
	{
		this.risk = risk;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RiskMaintenanceRequest [getRisk()=" + getRisk() + ", getUserContext()=" + getUserContext() + "]";
	}

}
