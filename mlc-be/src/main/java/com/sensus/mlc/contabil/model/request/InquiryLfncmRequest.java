package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfncm.model.Lfncm;

/**
 * The Class InquiryLfncmRequest.
 *
 * @author - Washinton
 */
public class InquiryLfncmRequest extends InquiryPaginationRequest
{

	/** The lfncm. */
	private Lfncm lfncm;

	/**
	 * Instantiates a new inquiry lfncm request.
	 */
	public InquiryLfncmRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfncm request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfncmRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfncm.
	 * 
	 * @param lfncmObject the new lfncm
	 */
	public void setLfncm(Lfncm lfncmObject)
	{
		lfncm = lfncmObject;
	}

	/**
	 * Gets the lfncm.
	 * 
	 * @return the lfncm
	 */
	public Lfncm getLfncm()
	{
		return lfncm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfncmRequest [getLfncm()=" + getLfncm() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

