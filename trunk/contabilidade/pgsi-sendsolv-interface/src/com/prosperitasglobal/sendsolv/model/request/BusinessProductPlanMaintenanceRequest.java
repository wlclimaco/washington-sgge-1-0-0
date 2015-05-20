package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;

/**
 * The Class BusinessProductPlanRequest.
 */
public class BusinessProductPlanMaintenanceRequest extends MaintenanceRequest
{
	/** The business product plan. */
	private BusinessProductPlan businessProductPlan;

	/**
	 * Get the business product plan.
	 *
	 * @return The business product plan.
	 */
	public BusinessProductPlan getBusinessProductPlan()
	{
		return businessProductPlan;
	}

	/**
	 * Set the business product plan.
	 *
	 * @param businessProductPlan The business product plan to set.
	 */
	public void setBusinessProductPlan(BusinessProductPlan businessProductPlan)
	{
		this.businessProductPlan = businessProductPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BusinessProductPlanMaintenanceRequest [getBusinessProductPlan()=" + getBusinessProductPlan()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
