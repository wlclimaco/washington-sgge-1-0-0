package com.qat.samples.sysmgmt.entidade;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class TarefaEnt extends ModelCosmeDamiao
{
	private Long mes;

	private Tarefa tarefa;
	
	private Note note;
	
	private boolean completo;

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
	public TarefaEnt()
	{
		super();
	}

	@Override
	public String toString()
	{
		return "TarefaEnt [getDepositoList()=" + getDepositoList() + ", toString()=" + super.toString() + "]";
	}

}
