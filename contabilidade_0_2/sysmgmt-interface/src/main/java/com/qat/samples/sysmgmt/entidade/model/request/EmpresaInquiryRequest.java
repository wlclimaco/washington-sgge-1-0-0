package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.criteria.EmpresaCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class EmpresaInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private EmpresaCriteria criteria;

	public EmpresaInquiryRequest()
	{
		super();
	}

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public EmpresaCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new EmpresaCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(EmpresaCriteria criteria)
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
		return "EmpresaInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
