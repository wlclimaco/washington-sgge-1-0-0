package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;

/**
 * The Class TemplateProductPlanMaintenanceRequest.
 */
public class TemplateProductPlanMaintenanceRequest extends MaintenanceRequest
{
	/** The template product plan. */
	private TemplateProductPlan templateProductPlan;

	/**
	 * Get the template product plan.
	 *
	 * @return The template product plan.
	 */
	public TemplateProductPlan getTemplateProductPlan()
	{
		return templateProductPlan;
	}

	/**
	 * Set the template product plan.
	 *
	 * @param templateProductPlan The template product plan to set.
	 */
	public void setTemplateProductPlan(TemplateProductPlan templateProductPlan)
	{
		this.templateProductPlan = templateProductPlan;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TemplateProductPlanMaintenanceRequest [getTemplateProductPlan()=" + getTemplateProductPlan()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
