package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.criteria.PessoaCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class PessoaInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private PessoaCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public PessoaCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new PessoaCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(PessoaCriteria criteria)
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
		return "PessoaInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
