package com.qat.samples.sysmgmt.advocacia;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Audiencia extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer dataAudiencia;

	private String descricao;

	private List<Note> noteLIst;

	public Audiencia()
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

	public Integer getDataAudiencia()
	{
		return dataAudiencia;
	}

	public void setDataAudiencia(Integer dataAudiencia)
	{
		this.dataAudiencia = dataAudiencia;
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
		return "Audiencia [getId()=" + getId() + ", getDataAudiencia()=" + getDataAudiencia() + ", getDescricao()="
				+ getDescricao() + ", getNoteLIst()=" + getNoteLIst() + ", toString()=" + super.toString() + "]";
	}

}
