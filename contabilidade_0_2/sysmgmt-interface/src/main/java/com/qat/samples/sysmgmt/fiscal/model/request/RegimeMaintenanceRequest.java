package com.qat.samples.sysmgmt.fiscal.model.request;

import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class RegimeMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Regime regime;

	/**
	 * The Constructor.
	 */
	public RegimeMaintenanceRequest()
	{

	}

	public Regime getRegime() {
		return regime;
	}

	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	@Override
	public String toString() {
		return "RegimeMaintenanceRequest [getRegime()=" + getRegime() + ", toString()=" + super.toString() + "]";
	}


}