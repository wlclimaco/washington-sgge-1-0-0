package com.qat.samples.sysmgmt.entidade.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Filial extends Entidade
{

	private List<Deposito> depositoList;

	/**
	 * @return the depositoList
	 */
	public List<Deposito> getDepositoList()
	{
		return depositoList;
	}

	/**
	 * @param depositoList the depositoList to set
	 */
	public void setDepositoList(List<Deposito> depositoList)
	{
		this.depositoList = depositoList;
	}

	/**
	 * Default constructor.
	 */
	public Filial()
	{
		super();
	}

	public Filial(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Filial [getDepositoList()=" + getDepositoList() + ", toString()=" + super.toString() + "]";
	}

}
