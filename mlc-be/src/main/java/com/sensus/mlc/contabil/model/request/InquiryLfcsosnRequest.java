package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfcsosn.model.Lfcsosn;

/**
 * The Class InquiryLfcsosnRequest.
 *
 * @author - Washinton
 */
public class InquiryLfcsosnRequest extends InquiryPaginationRequest
{

	/** The lfcsosn. */
	private Lfcsosn lfcsosn;

	/**
	 * Instantiates a new inquiry lfcsosn request.
	 */
	public InquiryLfcsosnRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfcsosn request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfcsosnRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfcsosn.
	 * 
	 * @param lfcsosnObject the new lfcsosn
	 */
	public void setLfcsosn(Lfcsosn lfcsosnObject)
	{
		lfcsosn = lfcsosnObject;
	}

	/**
	 * Gets the lfcsosn.
	 * 
	 * @return the lfcsosn
	 */
	public Lfcsosn getLfcsosn()
	{
		return lfcsosn;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfcsosnRequest [getLfcsosn()=" + getLfcsosn() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

