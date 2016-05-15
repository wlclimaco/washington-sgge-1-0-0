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
public class ContasReceber extends Titulo
{
	/** The SendSolv id for the account. */

	/** The description. */
	private Cliente cliente;

	/**
	 * Default constructor.
	 */
	public ContasReceber()
	{
		super();
	}

	public ContasReceber(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "ContasReceber [getCliente()=" + getCliente() + ", toString()=" + super.toString() + "]";
	}



}
