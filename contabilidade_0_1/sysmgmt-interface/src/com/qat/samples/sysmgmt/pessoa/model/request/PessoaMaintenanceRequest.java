package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.Pessoa;

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