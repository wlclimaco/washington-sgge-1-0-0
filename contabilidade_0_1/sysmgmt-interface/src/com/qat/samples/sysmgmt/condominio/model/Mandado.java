package com.qat.samples.sysmgmt.advocacia.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.Note;

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

	private List<Salario>

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

	public Integer getDataMandado()
	{
		return dataMandado;
	}

	public void setDataMandado(Integer dataMandado)
	{
		this.dataMandado = dataMandado;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public List<Note> getNoteLIst()
	{
		return noteLIst;
	}

	public void setNoteLIst(List<Note> noteLIst)
	{
		this.noteLIst = noteLIst;
	}

	@Override
	public String toString()
	{
		return "Mandado [getId()=" + getId() + ", getDataMandado()=" + getDataMandado() + ", getDescricao()="
				+ getDescricao() + ", getNoteLIst()=" + getNoteLIst() + ", toString()=" + super.toString() + "]";
	}

}
