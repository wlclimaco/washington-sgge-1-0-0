package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;

public class PessoaMaintenanceRequest extends Request
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

	@Override
	public String toString() {
		return "PessoaMaintenanceRequest [getPessoa()=" + getPessoa() + ", toString()=" + super.toString() + "]";
	}
}