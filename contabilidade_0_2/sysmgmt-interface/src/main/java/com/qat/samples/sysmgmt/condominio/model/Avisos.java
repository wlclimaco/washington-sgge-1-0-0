package com.qat.samples.sysmgmt.condominio.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Avisos extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Long dataAviso;

	private List<Note> noteList;

	public Avisos()
	{
		super();

	}

	public Avisos(int i, String string) {
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

	public Long getDataAviso()
	{
		return dataAviso;
	}

	public void setDataAviso(Long dataAviso)
	{
		this.dataAviso = dataAviso;
	}

	public List<Note> getNoteList()
	{
		return noteList;
	}

	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	@Override
	public String toString()
	{
		return "Avisos [getId()=" + getId() + ", getDataAviso()=" + getDataAviso() + ", getNoteList()=" + getNoteList()
				+ ", toString()=" + super.toString() + "]";
	}

}
