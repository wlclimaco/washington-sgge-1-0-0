package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Cadastro;

/**
 * The Model Object PagedInquiryRequest.
 */
public class CadastroInquiryRequest extends InquiryRequest
{

	/** The cadastro. */
	private Cadastro cadastro;

	/**
	 * Instantiates a new cadastro inquiry request.
	 */
	public CadastroInquiryRequest()
	{

	}

	/**
	 * Gets the cadastro.
	 * 
	 * @return the cadastro
	 */
	public Cadastro getCadastro()
	{
		return cadastro;
	}

	/**
	 * Sets the cadastro.
	 * 
	 * @param cadastro the new cadastro
	 */
	public void setCadastro(Cadastro cadastro)
	{
		this.cadastro = cadastro;
	}

	/**
	 * Instantiates a new cadastro inquiry request.
	 * 
	 * @param cadastro the cadastro
	 */
	public CadastroInquiryRequest(Cadastro cadastro)
	{
		super();
		this.cadastro = cadastro;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CadastroInquiryRequest [getCadastro()=" + getCadastro() + ", toString()=" + super.toString() + "]";
	}

}
