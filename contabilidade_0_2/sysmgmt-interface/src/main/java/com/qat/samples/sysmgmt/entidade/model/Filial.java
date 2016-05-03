package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
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

	public Filial(Integer id,String nome) {
		super();
		setId(id);
		setNome(nome);
		setEntidadeEnum(EntidadeTypeEnum.ADVOCACIA);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	@Override
	public String toString()
	{
		return "Filial [getDepositoList()=" + getDepositoList() + ", toString()=" + super.toString() + "]";
	}

}
