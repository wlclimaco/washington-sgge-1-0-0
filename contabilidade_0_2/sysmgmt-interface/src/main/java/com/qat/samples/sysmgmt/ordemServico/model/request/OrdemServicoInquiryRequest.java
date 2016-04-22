package com.qat.samples.sysmgmt.ordemServico.model.request;

import com.qat.samples.sysmgmt.ordemServico.model.criteria.OrdemServicoCriteria;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

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

	@Override
	public String toString()
	{
		return "OrdemServicoInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
