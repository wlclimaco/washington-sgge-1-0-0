package com.qat.samples.sysmgmt.financeiro.model;

import java.util.List;

import com.qat.samples.sysmgmt.nf.model.NotaFiscalEntrada;
import com.qat.samples.sysmgmt.nf.model.NotaFiscalSaida;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ContasPagar extends Titulo
{
	/** The type of an account. */
	private Fornecedor fornecedor;


	/**
	 * Default constructor.
	 */
	public ContasPagar()
	{
		super();
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	@Override
	public String toString() {
		return "ContasPagar [getFornecedor()=" + getFornecedor() + ", toString()=" + super.toString() + "]";
	}


}
