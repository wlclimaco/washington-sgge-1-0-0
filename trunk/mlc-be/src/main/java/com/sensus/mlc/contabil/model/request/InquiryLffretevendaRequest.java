package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lffretevenda.model.Lffretevenda;

/**
 * The Class InquiryLffretevendaRequest.
 *
 * @author - Washinton
 */
public class InquiryLffretevendaRequest extends InquiryPaginationRequest
{

	/** The lffretevenda. */
	private Lffretevenda lffretevenda;

	/**
	 * Instantiates a new inquiry lffretevenda request.
	 */
	public InquiryLffretevendaRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lffretevenda request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLffretevendaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lffretevenda.
	 * 
	 * @param lffretevendaObject the new lffretevenda
	 */
	public void setLffretevenda(Lffretevenda lffretevendaObject)
	{
		lffretevenda = lffretevendaObject;
	}

	/**
	 * Gets the lffretevenda.
	 * 
	 * @return the lffretevenda
	 */
	public Lffretevenda getLffretevenda()
	{
		return lffretevenda;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLffretevendaRequest [getLffretevenda()=" + getLffretevenda() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

