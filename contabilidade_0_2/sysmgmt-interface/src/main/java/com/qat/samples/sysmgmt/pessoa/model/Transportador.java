package com.qat.samples.sysmgmt.pessoa.model;

import java.util.Date;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Transportador extends Pessoa
{

	/**
	 * Default constructor.
	 */
	public Transportador()
	{
		super();
	}

	public Transportador(Integer id)
	{
		super();
		setId(id);
	}

	public Transportador(int i, String string) {
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	@Override
	public String toString()
	{
		return "Transportador [toString()=" + super.toString() + "]";
	}

}
