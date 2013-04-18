package com.sensus.mlc.tabela.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tabela.model.Dominios;

/**
 * The Class InquiryDominiosRequest.
 *
 * @author - Washinton
 */
public class InquiryDominiosRequest extends InquiryPaginationRequest
{

	/** The dominios. */
	private Dominios dominios;

	/**
	 * Instantiates a new inquiry dominios request.
	 */
	public InquiryDominiosRequest()
	{
	}

	/**
	 * Instantiates a new inquiry dominios request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryDominiosRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the dominios.
	 * 
	 * @param dominiosObject the new dominios
	 */
	public void setDominios(Dominios dominiosObject)
	{
		dominios = dominiosObject;
	}

	/**
	 * Gets the dominios.
	 * 
	 * @return the dominios
	 */
	public Dominios getDominios()
	{
		return dominios;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryDominiosRequest [getDominios()=" + getDominios() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

