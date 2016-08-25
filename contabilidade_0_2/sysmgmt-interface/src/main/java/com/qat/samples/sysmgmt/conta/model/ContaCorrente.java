package com.qat.samples.sysmgmt.conta.model;

import java.util.List;

import com.qat.samples.sysmgmt.agencia.model.Agencia;
import com.qat.samples.sysmgmt.historico.model.HistoricoMovimento;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ContaCorrente extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;
	

	/** The type of an account. */
	private String numeroConta;
	private String nossoNumero;
	
	private Integer statusConta;

	private double saldo;

	private List<HistoricoMovimento> historicoList;

	public ContaCorrente()
	{

	}

	public ContaCorrente(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<HistoricoMovimento> getHistoricoList() {
		return historicoList;
	}

	public void setHistoricoList(List<HistoricoMovimento> historicoList) {
		this.historicoList = historicoList;
	}

	public Integer getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(Integer statusConta) {
		this.statusConta = statusConta;
	}

	@Override
	public String toString() {
		return "ContaCorrente [getId()=" + getId() + ", getNumeroConta()=" + getNumeroConta() + ", getNossoNumero()="
				+ getNossoNumero() + ", getSaldo()=" + getSaldo() + ", getHistoricoList()=" + getHistoricoList()
				+ ", getStatusConta()=" + getStatusConta() + ", toString()=" + super.toString() + "]";
	}

	
}
