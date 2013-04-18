package com.sensus.mlc.tabela.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tabela.model.Atributos;

/**
 * The Class InquiryAtributosRequest.
 *
 * @author - Washinton
 */
public class InquiryAtributosRequest extends InquiryPaginationRequest
{

	/** The atributos. */
	private Atributos atributos;

	/**
	 * Instantiates a new inquiry atributos request.
	 */
	public InquiryAtributosRequest()
	{
	}

	/**
	 * Instantiates a new inquiry atributos request.
	 *
	 * @param userContext the user context
	 */
	public InquiryAtributosRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the atributos.
	 *
	 * @param atributosObject the new atributos
	 */
	public void setAtributos(Atributos atributosObject)
	{
		atributos = atributosObject;
	}

	/**
	 * Gets the atributos.
	 *
	 * @return the atributos
	 */
	public Atributos getAtributos()
	{
		return atributos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryAtributosRequest [getAtributos()=" + getAtributos() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

