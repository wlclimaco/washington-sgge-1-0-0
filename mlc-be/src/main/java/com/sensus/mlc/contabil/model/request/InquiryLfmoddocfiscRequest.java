package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfmoddocfisc.model.Lfmoddocfisc;

/**
 * The Class InquiryLfmoddocfiscRequest.
 *
 * @author - Washinton
 */
public class InquiryLfmoddocfiscRequest extends InquiryPaginationRequest
{

	/** The lfmoddocfisc. */
	private Lfmoddocfisc lfmoddocfisc;

	/**
	 * Instantiates a new inquiry lfmoddocfisc request.
	 */
	public InquiryLfmoddocfiscRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfmoddocfisc request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfmoddocfiscRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfmoddocfisc.
	 * 
	 * @param lfmoddocfiscObject the new lfmoddocfisc
	 */
	public void setLfmoddocfisc(Lfmoddocfisc lfmoddocfiscObject)
	{
		lfmoddocfisc = lfmoddocfiscObject;
	}

	/**
	 * Gets the lfmoddocfisc.
	 * 
	 * @return the lfmoddocfisc
	 */
	public Lfmoddocfisc getLfmoddocfisc()
	{
		return lfmoddocfisc;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfmoddocfiscRequest [getLfmoddocfisc()=" + getLfmoddocfisc() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

