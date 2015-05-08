package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class BusinessProductPlanResponse.
 */
public class BusinessProductPlanResponse extends InquiryResponse
{
	/** The list of business product plans. */
	private List<BusinessProductPlan> businessProductPlanList;

	/**
	 * Get the list of business product plans.
	 *
	 * @return The business product plan list.
	 */
	public List<BusinessProductPlan> getBusinessProductPlanList()
	{
		return businessProductPlanList;
	}

	/**
	 * Set the list of business product plans.
	 *
	 * @param businessProductPlanList The business product plan list to set.
	 */
	public void setBusinessProductPlanList(List<BusinessProductPlan> businessProductPlanList)
	{
		this.businessProductPlanList = businessProductPlanList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setBusinessProductPlanList((List<BusinessProductPlan>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BusinessProductPlanResponse [getBusinessProductPlanList()=" + getBusinessProductPlanList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
