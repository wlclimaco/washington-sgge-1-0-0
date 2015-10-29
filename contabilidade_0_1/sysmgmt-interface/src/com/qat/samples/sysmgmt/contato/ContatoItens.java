package com.qat.samples.sysmgmt.contato;

import java.util.List;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ContatoItens extends ModelCosmeDamiao
{

	/** The SendSolv id for the account. */
	private Integer id;

	private String texto;

	private List<Note> noteList;

	/**
	 * Default constructor.
	 */
	public ContatoItens()
	{
		super();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the noteList
	 */
	public List<Note> getNoteList()
	{
		return noteList;
	}

	/**
	 * @param noteList the noteList to set
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/**
	 * @return the texto
	 */
	public String getTexto()
	{
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	@Override
	public String toString()
	{
		return "ContatoItens [getId()=" + getId() + ", getNoteList()=" + getNoteList() + ", getTexto()=" + getTexto()
				+ ", toString()=" + super.toString() + "]";
	}

}
