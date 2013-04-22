package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.lfitcalccusto.model.Lfitcalccusto;

/**
 * The Class InquiryLfitcalccustoRequest.
 *
 * @author - Washinton
 */
public class InquiryLfitcalccustoRequest extends InquiryPaginationRequest
{

	/** The lfitcalccusto. */
	private Lfitcalccusto lfitcalccusto;

	/**
	 * Instantiates a new inquiry lfitcalccusto request.
	 */
	public InquiryLfitcalccustoRequest()
	{
	}

	/**
	 * Instantiates a new inquiry lfitcalccusto request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryLfitcalccustoRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the lfitcalccusto.
	 * 
	 * @param lfitcalccustoObject the new lfitcalccusto
	 */
	public void setLfitcalccusto(Lfitcalccusto lfitcalccustoObject)
	{
		lfitcalccusto = lfitcalccustoObject;
	}

	/**
	 * Gets the lfitcalccusto.
	 * 
	 * @return the lfitcalccusto
	 */
	public Lfitcalccusto getLfitcalccusto()
	{
		return lfitcalccusto;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLfitcalccustoRequest [getLfitcalccusto()=" + getLfitcalccusto() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

