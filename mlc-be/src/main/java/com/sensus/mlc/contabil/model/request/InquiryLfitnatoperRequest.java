package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfitnatoper.model.Lfitnatoper;

/**
 * The Class InquiryLfitnatoperRequest.
 *
 * @author - Washinton
 */
public class InquiryLfitnatoperRequest extends InquiryPaginationRequest
{

	/** The lfitnatoper. */
	private Lfitnatoper lfitnatoper;

	/**
	 * Instantiates a new inquiry lfitnatoper request.
	 */
	public InquiryLfitnatoperRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfitnatoper request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfitnatoperRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfitnatoper.
	 * 
	 * @param lfitnatoperObject the new lfitnatoper
	 */
	public void setLfitnatoper(Lfitnatoper lfitnatoperObject)
	{
		lfitnatoper = lfitnatoperObject;
	}

	/**
	 * Gets the lfitnatoper.
	 * 
	 * @return the lfitnatoper
	 */
	public Lfitnatoper getLfitnatoper()
	{
		return lfitnatoper;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfitnatoperRequest [getLfitnatoper()=" + getLfitnatoper() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

