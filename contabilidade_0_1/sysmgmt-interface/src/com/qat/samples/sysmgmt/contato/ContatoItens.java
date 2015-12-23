package com.qat.samples.sysmgmt.contato;

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

	private Long dataAlt;

	private ContatoStatusEnum contatoStatus;

	private String note;

	private String titulo;

	/**
	 * Default constructor.
	 */
	public ContatoItens()
	{
		super();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Long getDataAlt()
	{
		return dataAlt;
	}

	public void setDataAlt(Long dataAlt)
	{
		this.dataAlt = dataAlt;
	}

	public ContatoStatusEnum getContatoStatus()
	{
		return contatoStatus;
	}

	public void setContatoStatus(ContatoStatusEnum contatoStatus)
	{
		this.contatoStatus = contatoStatus;
	}

	public Integer getContatoStatusValue()
	{
		if (contatoStatus != null)
		{
			return contatoStatus.getValue();
		}
		return null;
	}

	/**
	 * Sets the motivo value.
	 * 
	 * @param acaoTypeValue the new motivo value
	 */
	public void setContatoStatusValue(Integer acaoTypeValue)
	{
		contatoStatus = ContatoStatusEnum.enumForValue(acaoTypeValue);
	}

	public Note getNote()
	{
		return note;
	}

	public void setNote(Note note)
	{
		this.note = note;
	}

	@Override
	public String toString()
	{
		return "ContatoItens [getId()=" + getId() + ", getDataAlt()=" + getDataAlt() + ", getContatoStatus()="
				+ getContatoStatus() + ", getContatoStatusValue()=" + getContatoStatusValue() + ", getNote()="
				+ getNote() + ", toString()=" + super.toString() + "]";
	}

}
