package com.prosperitasglobal.sendsolv.model;

import java.util.List;

import com.prosperitasglobal.cbof.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ContatoItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private List<Note> noteList;

	private Long DataContato;

	private String nomeContato;

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
	 * @return the dataContato
	 */
	public Long getDataContato()
	{
		return DataContato;
	}

	/**
	 * @param dataContato the dataContato to set
	 */
	public void setDataContato(Long dataContato)
	{
		DataContato = dataContato;
	}

	/**
	 * @return the nomeContato
	 */
	public String getNomeContato()
	{
		return nomeContato;
	}

	/**
	 * @param nomeContato the nomeContato to set
	 */
	public void setNomeContato(String nomeContato)
	{
		this.nomeContato = nomeContato;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContatoItens [getId()=" + getId() + ", getNoteList()=" + getNoteList() + ", getDataContato()="
				+ getDataContato() + ", getNomeContato()=" + getNomeContato() + ", getTabelaEnumValue()="
				+ getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()="
				+ getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite() + ", getProcessId()="
				+ getProcessId() + ", getUserId()=" + getUserId() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
