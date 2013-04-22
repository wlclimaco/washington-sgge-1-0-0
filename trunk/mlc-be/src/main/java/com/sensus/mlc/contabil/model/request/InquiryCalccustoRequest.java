package com.sensus.mlc.contabil.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.calccusto.model.Calccusto;

/**
 * The Class InquiryCalccustoRequest.
 *
 * @author - Washinton
 */
public class InquiryCalccustoRequest extends InquiryPaginationRequest
{

	/** The calccusto. */
	private Calccusto calccusto;

	/**
	 * Instantiates a new inquiry calccusto request.
	 */
	public InquiryCalccustoRequest()
	{
	}

	/**
	 * Instantiates a new inquiry calccusto request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryCalccustoRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the calccusto.
	 * 
	 * @param calccustoObject the new calccusto
	 */
	public void setCalccusto(Calccusto calccustoObject)
	{
		calccusto = calccustoObject;
	}

	/**
	 * Gets the calccusto.
	 * 
	 * @return the calccusto
	 */
	public Calccusto getCalccusto()
	{
		return calccusto;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryCalccustoRequest [getCalccusto()=" + getCalccusto() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

