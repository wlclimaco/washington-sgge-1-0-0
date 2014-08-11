package com.qat.samples.sysmgmt.produto.model.request;

import java.util.List;

import com.qat.framework.model.request.InquiryRequest;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.util.Criteria;

/**
 * The Model Object PagedInquiryRequest.
 */
public class CadastroInquiryRequest extends InquiryRequest
{

	/** The cadastro. */
	private Cadastro cadastro;

	/** The criteria. */
	private List<Criteria> criteria;

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

	public List<Criteria> getCriteria()
	{
		return criteria;
	}

	public void setCriteria(List<Criteria> criteria)
	{
		this.criteria = criteria;
	}

	@Override
	public String toString()
	{
		return "CadastroInquiryRequest [getCadastro()=" + getCadastro() + ", getCriteria()=" + getCriteria()
				+ ", toString()=" + super.toString() + "]";
	}

}
