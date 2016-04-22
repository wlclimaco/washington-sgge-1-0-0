package com.qat.samples.sysmgmt.fiscal.model.request;

import com.qat.samples.sysmgmt.estado.model.criteria.EstadoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class RegimeInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private EstadoCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public EstadoCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new EstadoCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(EstadoCriteria criteria)
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
		return "EstadoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
