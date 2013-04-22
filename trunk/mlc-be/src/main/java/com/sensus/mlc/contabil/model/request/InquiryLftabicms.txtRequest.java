package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lftabicms.txt.model.Lftabicms.txt;

/**
 * The Class InquiryLftabicms.txtRequest.
 *
 * @author - Washinton
 */
public class InquiryLftabicms.txtRequest extends InquiryPaginationRequest
{

	/** The lftabicms.txt. */
	private Lftabicms.txt lftabicms.txt;

	/**
	 * Instantiates a new inquiry lftabicms.txt request.
	 */
	public InquiryLftabicms.txtRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lftabicms.txt request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLftabicms.txtRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lftabicms.txt.
	 * 
	 * @param lftabicms.txtObject the new lftabicms.txt
	 */
	public void setLftabicms.txt(Lftabicms.txt lftabicms.txtObject)
	{
		lftabicms.txt = lftabicms.txtObject;
	}

	/**
	 * Gets the lftabicms.txt.
	 * 
	 * @return the lftabicms.txt
	 */
	public Lftabicms.txt getLftabicms.txt()
	{
		return lftabicms.txt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLftabicms.txtRequest [getLftabicms.txt()=" + getLftabicms.txt() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

