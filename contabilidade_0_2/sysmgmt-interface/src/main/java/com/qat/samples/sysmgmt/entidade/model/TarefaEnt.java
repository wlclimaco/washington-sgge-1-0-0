package com.qat.samples.sysmgmt.entidade.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.Tarefa;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class TarefaEnt extends ModelCosmeDamiao
{
	private Long mes;

	private Tarefa tarefa;

	private Note note;

	private boolean completo;

	public Long getMes()
	{
		return mes;
	}

	public void setMes(Long mes)
	{
		this.mes = mes;
	}

	public Tarefa getTarefa()
	{
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa)
	{
		this.tarefa = tarefa;
	}

	public Note getNote()
	{
		return note;
	}

	public void setNote(Note note)
	{
		this.note = note;
	}

	public boolean isCompleto()
	{
		return completo;
	}

	public void setCompleto(boolean completo)
	{
		this.completo = completo;
	}

	@Override
	public String toString()
	{
		return "TarefaEnt [getMes()=" + getMes() + ", getTarefa()=" + getTarefa() + ", getNote()=" + getNote()
				+ ", isCompleto()=" + isCompleto() + ", toString()=" + super.toString() + "]";
	}

}
