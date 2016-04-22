package com.qat.samples.sysmgmt.condominio.model;

import java.util.List;

import com.qat.samples.sysmgmt.pessoa.model.Pessoa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Sindico extends Pessoa
{
	private Integer id;
	/** The SendSolv id for the account. */
	private List<Mandado> mandatoList;

	public Sindico()
	{

	}

	public Sindico(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer getId()
	{
		return id;
	}

	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	public List<Mandado> getMandatoList()
	{
		return mandatoList;
	}

	public void setMandatoList(List<Mandado> mandatoList)
	{
		this.mandatoList = mandatoList;
	}

	@Override
	public String toString()
	{
		return "Sindico [getId()=" + getId() + ", getMandatoList()=" + getMandatoList() + ", toString()="
				+ super.toString() + "]";
	}

}
