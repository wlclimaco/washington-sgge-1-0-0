package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class BusinessProductPlanResponse.
 */
public class TemplateProductPlanResponse extends InquiryResponse
{
	/** The list of template product plans. */
	private List<TemplateProductPlan> templateProductPlanList;

	/**
	 * Get the list of template product plans.
	 *
	 * @return The template product plan list.
	 */
	public List<TemplateProductPlan> getTemplateProductPlanList()
	{
		return templateProductPlanList;
	}

	/**
	 * Set the list of template product plans.
	 *
	 * @param templateProductPlanList The template product plan list to set.
	 */
	public void setTemplateProductPlanList(List<TemplateProductPlan> templateProductPlanList)
	{
		this.templateProductPlanList = templateProductPlanList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setTemplateProductPlanList((List<TemplateProductPlan>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TemplateProductPlanResponse [getTemplateProductPlanList()=" + getTemplateProductPlanList()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
