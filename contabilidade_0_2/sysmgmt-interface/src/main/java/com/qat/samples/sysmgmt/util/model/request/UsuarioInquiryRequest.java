package com.qat.samples.sysmgmt.util.model.request;

import com.qat.samples.sysmgmt.pessoa.model.criteria.ClienteCriteria;
import com.qat.samples.sysmgmt.util.model.criteria.ComumCriteria;

/**
 * The Class ProdutoInquiryRequest.
 */
public class UsuarioInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private ComumCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public ComumCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new ComumCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(ComumCriteria criteria)
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
