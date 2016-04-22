package com.qat.samples.sysmgmt.advocacia;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ProcessoStatus extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer dataProcesso;

	private Note note;

	private ProcessoTypeEnum statusProcess;

	public ProcessoStatus()
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
		return "ProcessoStatus [getId()=" + getId() + ", getDataProcesso()=" + getDataProcesso() + ", getNote()="
				+ getNote() + ", getStatusProcess()=" + getStatusProcess() + ", toString()=" + super.toString() + "]";
	}

}
