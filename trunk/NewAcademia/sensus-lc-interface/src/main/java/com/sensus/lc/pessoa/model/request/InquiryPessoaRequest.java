package com.sensus.lc.pessoa.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.pessoa.model.Pessoa;

/**
 * The Class InquiryPessoaRequest.
 * 
 * @author Washington
 */
public class InquiryPessoaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The pessoas. */
	private List<Pessoa> pessoas;

	/**
	 * Instantiates a new inquiry pessoa request.
	 */
	public InquiryPessoaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry pessoa request.
	 * 
	 * @param pessoa the pessoa
	 */
	public InquiryPessoaRequest(Pessoa pessoa)
	{
		addPessoa(pessoa);
	}

	/**
	 * Gets the pessoas.
	 * 
	 * @return the pessoas
	 */
	public List<Pessoa> getPessoas()
	{
		return pessoas;
	}

	/**
	 * Sets the pessoas.
	 * 
	 * @param pessoas the new pessoas
	 */
	public void setPessoas(List<Pessoa> pessoas)
	{
		this.pessoas = pessoas;
	}

	/**
	 * Gets the first pessoa.
	 * 
	 * @return the first pessoa
	 */
	public Pessoa getFirstPessoa()
	{
		if ((getPessoas() != null) && !getPessoas().isEmpty())
		{
			return getPessoas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the pessoa.
	 * 
	 * @param pessoa the pessoa
	 */
	public void addPessoa(Pessoa pessoa)
	{
		if (getPessoas() == null)
		{
			setPessoas(new ArrayList<Pessoa>());
		}

		getPessoas().add(pessoa);
	}

	@Override
	public String toString()
	{
		return "InquiryPessoaRequest [getPessoas()=" + getPessoas() + ", getFirstPessoa()=" + getFirstPessoa()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
