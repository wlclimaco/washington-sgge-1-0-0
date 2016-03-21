package com.qat.samples.sysmgmt.advocacia.model;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Sindico extends Pessoa
{
	/** The SendSolv id for the account. */
	private List<Mandato> mandatoList;

	public Sindico()
	{

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getDataProcesso()
	{
		return dataProcesso;
	}

	public void setDataProcesso(Integer dataProcesso)
	{
		this.dataProcesso = dataProcesso;
	}

	public Note getNote()
	{
		return note;
	}

	public void setNote(Note note)
	{
		this.note = note;
	}

	public ProcessoTypeEnum getStatusProcess()
	{
		return statusProcess;
	}

	public void setStatusProcess(ProcessoTypeEnum statusProcess)
	{
		this.statusProcess = statusProcess;
	}

	@Override
	public String toString()
	{
		return "Sindico [getId()=" + getId() + ", getDataProcesso()=" + getDataProcesso() + ", getNote()="
				+ getNote() + ", getStatusProcess()=" + getStatusProcess() + ", toString()=" + super.toString() + "]";
	}

}
