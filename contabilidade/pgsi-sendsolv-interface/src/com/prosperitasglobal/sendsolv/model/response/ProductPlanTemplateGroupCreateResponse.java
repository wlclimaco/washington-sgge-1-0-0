package com.prosperitasglobal.sendsolv.model.response;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.qat.framework.model.response.Response;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class ProductPlanTemplateGroupCreateResponse.
 */
public class ProductPlanTemplateGroupCreateResponse extends Response
{
	/** The list of business product plans. */
	private List<BusinessProductPlan> businessProductPlanList;

	/**
	 * Get the list of business product plans.
	 *
	 * @return The list of business product plans.
	 */
	public List<BusinessProductPlan> getBusinessProductPlanList()
	{
		if (ValidationUtil.isNull(businessProductPlanList))
		{
			setBusinessProductPlanList(new ArrayList<BusinessProductPlan>());
		}

		return businessProductPlanList;
	}

	/**
	 * Set the list of business product plan.
	 *
	 * @param businessProductPlanList The list of business product plan to set.
	 */
	public void setBusinessProductPlanList(List<BusinessProductPlan> businessProductPlanList)
	{
		this.businessProductPlanList = businessProductPlanList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroupCreateResponse [getBusinessProductPlanList()=" + getBusinessProductPlanList()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
