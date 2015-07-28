package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.OrdemServicoCriteria;

/**
 * The Class OrdemServicoInquiryRequest.
 */
public class OrdemServicoInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private OrdemServicoCriteria criteria;

	public OrdemServicoInquiryRequest()
	{
		super();
	}

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public OrdemServicoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new OrdemServicoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(OrdemServicoCriteria criteria)
	{
		this.criteria = criteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrdemServicoInquiryRequest [getCriteria()=" + getCriteria() + ", getParentId()=" + getParentId()
				+ ", getInquiryCriteria()=" + getInquiryCriteria() + ", getStatusList()=" + getStatusList()
				+ ", toString()=" + super.toString() + ", getPageSize()=" + getPageSize() + ", getStartPage()="
				+ getStartPage() + ", getSortExpressions()=" + getSortExpressions() + ", getSortExpression()="
				+ getSortExpression() + ", isPreQueryCount()=" + isPreQueryCount() + ", getMaxPreQueryCount()="
				+ getMaxPreQueryCount() + ", getUserContext()=" + getUserContext() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
