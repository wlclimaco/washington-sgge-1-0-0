package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Embalagem;

/**
 * The Model Object PagedInquiryRequest.
 */
public class EmbalagemInquiryRequest extends InquiryRequest
{

	/** The cadastro. */
	private Embalagem embalagem;

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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CadastroInquiryRequest [getEmbalagem()=" + getEmbalagem() + ", toString()=" + super.toString() + "]";
	}

}
