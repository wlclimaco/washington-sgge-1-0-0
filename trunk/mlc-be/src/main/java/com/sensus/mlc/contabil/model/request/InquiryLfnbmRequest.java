package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfnbm.model.Lfnbm;

/**
 * The Class InquiryLfnbmRequest.
 *
 * @author - Washinton
 */
public class InquiryLfnbmRequest extends InquiryPaginationRequest
{

	/** The lfnbm. */
	private Lfnbm lfnbm;

	/**
	 * Instantiates a new inquiry lfnbm request.
	 */
	public InquiryLfnbmRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfnbm request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfnbmRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfnbm.
	 * 
	 * @param lfnbmObject the new lfnbm
	 */
	public void setLfnbm(Lfnbm lfnbmObject)
	{
		lfnbm = lfnbmObject;
	}

	/**
	 * Gets the lfnbm.
	 * 
	 * @return the lfnbm
	 */
	public Lfnbm getLfnbm()
	{
		return lfnbm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfnbmRequest [getLfnbm()=" + getLfnbm() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

