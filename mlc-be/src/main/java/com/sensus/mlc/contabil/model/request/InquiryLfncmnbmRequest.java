package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfncmnbm.model.Lfncmnbm;

/**
 * The Class InquiryLfncmnbmRequest.
 *
 * @author - Washinton
 */
public class InquiryLfncmnbmRequest extends InquiryPaginationRequest
{

	/** The lfncmnbm. */
	private Lfncmnbm lfncmnbm;

	/**
	 * Instantiates a new inquiry lfncmnbm request.
	 */
	public InquiryLfncmnbmRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfncmnbm request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfncmnbmRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfncmnbm.
	 * 
	 * @param lfncmnbmObject the new lfncmnbm
	 */
	public void setLfncmnbm(Lfncmnbm lfncmnbmObject)
	{
		lfncmnbm = lfncmnbmObject;
	}

	/**
	 * Gets the lfncmnbm.
	 * 
	 * @return the lfncmnbm
	 */
	public Lfncmnbm getLfncmnbm()
	{
		return lfncmnbm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfncmnbmRequest [getLfncmnbm()=" + getLfncmnbm() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

