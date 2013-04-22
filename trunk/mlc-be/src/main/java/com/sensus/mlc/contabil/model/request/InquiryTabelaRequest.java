package com.sensus.mlc.tabela.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.tabela.model.Tabela;

/**
 * The Class InquiryTabelaRequest.
 *
 * @author - Washinton
 */
public class InquiryTabelaRequest extends InquiryPaginationRequest
{

	/** The tabela. */
	private Tabela tabela;

	/**
	 * Instantiates a new inquiry tabela request.
	 */
	public InquiryTabelaRequest()
	{
	}

	/**
	 * Instantiates a new inquiry tabela request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryTabelaRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Sets the tabela.
	 * 
	 * @param tabelaObject the new tabela
	 */
	public void setTabela(Tabela tabelaObject)
	{
		tabela = tabelaObject;
	}

	/**
	 * Gets the tabela.
	 * 
	 * @return the tabela
	 */
	public Tabela getTabela()
	{
		return tabela;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryTabelaRequest [getTabela()=" + getTabela() + ", getStartRow()=" + getStartRow() + ", getEndRow()="
				+ getEndRow() + ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage()
				+ ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()=" + getSortExpression()
				+ ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount()
				+ ", getTenant()=" + getTenant() + ", getUserContext()=" + getUserContext() + "]";
	}
}

