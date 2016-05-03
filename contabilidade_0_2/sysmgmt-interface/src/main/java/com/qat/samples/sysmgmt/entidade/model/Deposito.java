package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Deposito extends Entidade
{

	/**
	 * Default constructor.
	 */
	public Deposito()
	{
		super();
	}

	public Deposito(Integer id,String nome) {
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
		return "Deposito [toString()=" + super.toString() + "]";
	}

}
