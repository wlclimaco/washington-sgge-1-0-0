package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.criteria.NotaFiscalCriteria;

/**
 * The Class NotaFiscalInquiryRequest.
 */
public class NotaFiscalInquiryRequest extends PagedInquiryRequest
{

	/** The criteria. */
	private NotaFiscalCriteria criteria;

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	public NotaFiscalCriteria getCriteria()
	{
		if (criteria == null)
		{
			criteria = new NotaFiscalCriteria();
		}
		return criteria;
	}

	/**
	 * Sets the criteria.
	 *
	 * @param criteria the criteria
	 */
	public void setCriteria(NotaFiscalCriteria criteria)
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
		return "NotaFiscalInquiryRequest [getCriteria()=" + getCriteria() + ", toString()=" + super.toString() + "]";
	}

}
