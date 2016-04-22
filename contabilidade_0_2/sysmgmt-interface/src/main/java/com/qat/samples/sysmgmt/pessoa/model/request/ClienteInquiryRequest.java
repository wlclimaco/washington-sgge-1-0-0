package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.criteria.ClienteCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class ClienteInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ClienteCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ClienteCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ClienteCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ClienteCriteria criteria)
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
		return "ClienteInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
