package com.qat.samples.sysmgmt.condominio.model;

import java.util.Date;
import java.util.List;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.samples.sysmgmt.pessoa.model.Pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Inquilino extends Pessoa
{

	private List<InquilinoRes> inquilinoResList;

	public Inquilino()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Inquilino(int i, String string) {
		setId(i);
		setNome(string);
		setModelAction(PersistenceActionEnum.INSERT);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public List<InquilinoRes> getInquilinoResList()
	{
		return inquilinoResList;
	}

	public void setInquilinoResList(List<InquilinoRes> inquilinoResList)
	{
		this.inquilinoResList = inquilinoResList;
	}

	@Override
	public String toString()
	{
		return "Inquilino [getInquilinoResList()=" + getInquilinoResList() + ", toString()=" + super.toString() + "]";
	}

}
