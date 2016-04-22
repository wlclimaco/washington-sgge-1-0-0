package com.qat.samples.sysmgmt.nf.model;

import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class NotaFiscalEntrada extends NotaFiscal
{
	/** The fornecedor. */
	private Fornecedor fornecedor;

	public NotaFiscalEntrada(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public NotaFiscalEntrada() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fornecedor
	 */
	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor(Fornecedor fornecedor)
	{
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString()
	{
		return "NotaFiscalEntrada [getFornecedor()=" + getFornecedor() + ", toString()=" + super.toString() + "]";
	}

}
