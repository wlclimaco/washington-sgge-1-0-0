package com.prosperitasglobal.sendsolv.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class ProductPlanTemplateGroupResponse.
 */
public class ProductPlanTemplateGroupResponse extends InquiryResponse
{
	/** The list of product plan template groups. */
	private List<ProductPlanTemplateGroup> productPlanTemplateGroupList;

	/**
	 * Get the list of product plan template groups.
	 *
	 * @return The product plan template group list.
	 */
	public List<ProductPlanTemplateGroup> getProductPlanTemplateGroupList()
	{
		return productPlanTemplateGroupList;
	}

	/**
	 * Set the list of product plan template groups.
	 *
	 * @param productPlanTemplateGroupList The product plan template group list to set.
	 */
	public void setProductPlanTemplateGroupList(List<ProductPlanTemplateGroup> productPlanTemplateGroupList)
	{
		this.productPlanTemplateGroupList = productPlanTemplateGroupList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setProductPlanTemplateGroupList((List<ProductPlanTemplateGroup>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanTemplateGroupResponse [getProductPlanTemplateGroupList()="
				+ getProductPlanTemplateGroupList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}
}
