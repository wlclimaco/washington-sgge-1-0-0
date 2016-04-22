package com.qat.samples.sysmgmt.condominio.model;

import java.util.List;

import com.qat.samples.sysmgmt.dp.model.Salario;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Mandado extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Long dataInicio;

	private Long dataFim;

	private List<Salario> salarioList;

	public Mandado()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Long getDataInicio()
	{
		return dataInicio;
	}

	public void setDataInicio(Long dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	public Long getDataFim()
	{
		return dataFim;
	}

	public void setDataFim(Long dataFim)
	{
		this.dataFim = dataFim;
	}

	public List<Salario> getSalarioList()
	{
		return salarioList;
	}

	public void setSalarioList(List<Salario> salarioList)
	{
		this.salarioList = salarioList;
	}

	@Override
	public String toString()
	{
		return "Mandado [getId()=" + getId() + ", getDataInicio()=" + getDataInicio() + ", getDataFim()="
				+ getDataFim() + ", getSalarioList()=" + getSalarioList() + ", toString()=" + super.toString() + "]";
	}

}
