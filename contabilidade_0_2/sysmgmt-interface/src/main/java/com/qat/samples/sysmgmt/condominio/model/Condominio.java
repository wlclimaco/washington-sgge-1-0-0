package com.qat.samples.sysmgmt.condominio.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.entidade.model.Entidade;
import com.qat.samples.sysmgmt.entidade.model.EntidadeTypeEnum;
import com.qat.samples.sysmgmt.entidade.model.TarefaEnt;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Condominio extends Entidade
{

	private List<ContaCorrente> contaCorrenteList;

	private List<TarefaEnt> tarefaList;

	private List<Sindico> sindicoList;

	public Condominio()
	{
		super();
	}

	public Condominio(Integer id,String nome) {
		super();
		setId(id);
		setNome(nome);
		setEntidadeEnum(EntidadeTypeEnum.CONDOMINIO);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public List<TarefaEnt> getTarefaList()
	{
		return tarefaList;
	}

	public void setTarefaList(List<TarefaEnt> tarefaList)
	{
		this.tarefaList = tarefaList;
	}

	public List<Sindico> getSindicoList()
	{
		return sindicoList;
	}

	public void setSindicoList(List<Sindico> sindicoList)
	{
		this.sindicoList = sindicoList;
	}

	public List<ContaCorrente> getContaCorrenteList()
	{
		return contaCorrenteList;
	}

	public void setContaCorrenteList(List<ContaCorrente> contaCorrenteList)
	{
		this.contaCorrenteList = contaCorrenteList;
	}

	@Override
	public String toString()
	{
		return "Condominio [getTarefaList()=" + getTarefaList() + ", getSindicoList()=" + getSindicoList()
				+ ", getContaCorrenteList()=" + getContaCorrenteList() + ", toString()=" + super.toString() + "]";
	}

}
