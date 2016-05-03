package com.qat.samples.sysmgmt.advocacia;

import java.util.Date;

import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.entidade.model.EntidadeTypeEnum;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Advocacia extends Entidade
{

	public Advocacia()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Advocacia(Integer id,String nome) {
		super();
		setId(id);
		setNome(nome);
		setEntidadeEnum(EntidadeTypeEnum.ADVOCACIA);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Advocacia(String string, String string2, Object object) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Advogado [toString()=" + super.toString() + "]";
	}

}
