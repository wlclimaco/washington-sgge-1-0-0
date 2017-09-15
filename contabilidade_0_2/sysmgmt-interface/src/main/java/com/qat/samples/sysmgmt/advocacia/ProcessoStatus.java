package com.qat.samples.sysmgmt.advocacia;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

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
	private Long dataProcesso;

	private String note;

	private DoisValores statusProcess;

	public ProcessoStatus()
	{

	}

	public ProcessoStatus(Long dataProcesso, String note, DoisValores statusProcess) {
		super();
		this.dataProcesso = dataProcesso;
		this.note = note;
		this.statusProcess = statusProcess;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Long getDataProcesso()
	{
		return dataProcesso;
	}

	public void setDataProcesso(Long dataProcesso)
	{
		this.dataProcesso = dataProcesso;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public DoisValores getStatusProcess()
	{
		return statusProcess;
	}

	public void setStatusProcess(DoisValores statusProcess)
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
