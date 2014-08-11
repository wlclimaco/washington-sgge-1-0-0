package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Embalagem;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class EmbalagemInquiryRequest extends InquiryRequest
{

	/** The cadastro. */
	private Embalagem embalagem;

	/** The criteria. */
	private Criteria criteria;

	/**
	 * Instantiates a new cadastro inquiry request.
	 */
	public EmbalagemInquiryRequest()
	{

	}

	public Embalagem getEmbalagem()
	{
		return embalagem;
	}

	public void setEmbalagem(Embalagem embalagem)
	{
		this.embalagem = embalagem;
	}

	/**
	 * Instantiates a new cadastro inquiry request.
	 * 
	 * @param cadastro the cadastro
	 */
	public EmbalagemInquiryRequest(Embalagem embalagem)
	{
		super();
		this.embalagem = embalagem;
	}

	public Criteria getCriteria()
	{
		return criteria;
	}

	public void setCriteria(Criteria criteria)
	{
		this.criteria = criteria;
	}

	@Override
	public String toString()
	{
		return "EmbalagemInquiryRequest [getEmbalagem()=" + getEmbalagem() + ", getCriteria()=" + getCriteria()
				+ ", toString()=" + super.toString() + "]";
	}

}
