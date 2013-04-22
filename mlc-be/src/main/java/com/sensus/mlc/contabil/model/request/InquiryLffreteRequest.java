package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lffrete.model.Lffrete;

/**
 * The Class InquiryLffreteRequest.
 *
 * @author - Washinton
 */
public class InquiryLffreteRequest extends InquiryPaginationRequest
{

	/** The lffrete. */
	private Lffrete lffrete;

	/**
	 * Instantiates a new inquiry lffrete request.
	 */
	public InquiryLffreteRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lffrete request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLffreteRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lffrete.
	 * 
	 * @param lffreteObject the new lffrete
	 */
	public void setLffrete(Lffrete lffreteObject)
	{
		lffrete = lffreteObject;
	}

	/**
	 * Gets the lffrete.
	 * 
	 * @return the lffrete
	 */
	public Lffrete getLffrete()
	{
		return lffrete;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLffreteRequest [getLffrete()=" + getLffrete() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

