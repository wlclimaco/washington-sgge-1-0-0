package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Pessoa;

public class PessoaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Pessoa pessoa;

	/**
	 * The Constructor.
	 */
	public PessoaMaintenanceRequest()
	{

	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa()
	{
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoMaintenanceRequest [getPessoa()=" + getPessoa() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}