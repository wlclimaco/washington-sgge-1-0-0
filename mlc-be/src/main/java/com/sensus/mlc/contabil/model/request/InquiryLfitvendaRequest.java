package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfitvenda.model.Lfitvenda;

/**
 * The Class InquiryLfitvendaRequest.
 *
 * @author - Washinton
 */
public class InquiryLfitvendaRequest extends InquiryPaginationRequest
{

	/** The lfitvenda. */
	private Lfitvenda lfitvenda;

	/**
	 * Instantiates a new inquiry lfitvenda request.
	 */
	public InquiryLfitvendaRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfitvenda request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfitvendaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfitvenda.
	 * 
	 * @param lfitvendaObject the new lfitvenda
	 */
	public void setLfitvenda(Lfitvenda lfitvendaObject)
	{
		lfitvenda = lfitvendaObject;
	}

	/**
	 * Gets the lfitvenda.
	 * 
	 * @return the lfitvenda
	 */
	public Lfitvenda getLfitvenda()
	{
		return lfitvenda;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfitvendaRequest [getLfitvenda()=" + getLfitvenda() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

