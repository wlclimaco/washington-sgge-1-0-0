package com.qat.samples.sysmgmt.beneficios.model.request;

import com.qat.samples.sysmgmt.beneficios.model.criteria.BeneficiosCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Class ProdutoInquiryRequest.
 */
public class BeneficiosInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private BeneficiosCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public BeneficiosCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new BeneficiosCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(BeneficiosCriteria criteria)
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
		return "ArquivoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
