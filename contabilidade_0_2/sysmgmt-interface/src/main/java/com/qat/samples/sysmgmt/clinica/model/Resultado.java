package com.qat.samples.sysmgmt.clinica.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Resultado extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private List<Note> notes;

	public Resultado()
	{

	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "Resultado [getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}

}
