package com.sensus.lc.pessoa.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.pessoa.model.Pessoa;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class PessoaRequest.
 * 
 * @author Washington
 */
public class PessoaRequest extends TenantRequest
{
	/** The pessoas. */
	private List<Pessoa> pessoas;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new pessoa request.
	 */
	public PessoaRequest()
	{
	}

	/**
	 * Instantiates a new pessoa request.
	 * 
	 * @param pessoa the pessoa
	 */
	public PessoaRequest(Pessoa pessoa)
	{
		addPessoa(pessoa);
	}

	/**
	 * Instantiates a new pessoa request.
	 * 
	 * @param userContext the user context
	 */
	public PessoaRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new pessoa request.
	 * 
	 * @param pessoa the pessoa
	 * @param userContext the user context
	 */
	public PessoaRequest(Pessoa pessoa, UserContext userContext)
	{
		addPessoa(pessoa);
		setUserContext(userContext);
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
	 * Gets the first pessoa.
	 * 
	 * @return the first pessoa
	 */
	public Pessoa getFirstPessoa()
	{
		if ((getPessoas() != null) && !getPessoas().isEmpty())
		{
			return getPessoas().get(0);
		}

		return null;
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

	/**
	 * Gets the retrieve history.
	 * 
	 * @return the retrieve history
	 */
	public Boolean getRetrieveHistory()
	{
		return retrieveHistory;
	}

	/**
	 * Sets the retrieve history.
	 * 
	 * @param retrieveHistory the new retrieve history
	 */
	public void setRetrieveHistory(Boolean retrieveHistory)
	{
		this.retrieveHistory = retrieveHistory;
	}

	@Override
	public String toString()
	{
		return "PessoaRequest [getPessoas()=" + getPessoas() + ", getFirstPessoa()=" + getFirstPessoa()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
