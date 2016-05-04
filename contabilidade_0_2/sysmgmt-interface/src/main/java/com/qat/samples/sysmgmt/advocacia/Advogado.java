package com.qat.samples.sysmgmt.advocacia;

import java.util.Date;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Advogado extends Pessoa
{

	public Advogado()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Advogado(int i, String string) {
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Advogado(String string, String string2, Object object) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "Advogado [toString()=" + super.toString() + "]";
	}

}
