package com.qat.samples.sysmgmt.advocacia;

import java.util.List;

import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Processo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer dataProcess;

	private Double valor;

	private List<Advogado> advogadoLIst;

	private List<Cliente> clienteList;

	private List<Audiencia> audienciaList;

	private List<ProcessoStatus> processoStatusList;

	public Processo()
	{
		super();

	}

	public Processo(int i, String string) {
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

	public Integer getDataProcess()
	{
		return dataProcess;
	}

	public void setDataProcess(Integer dataProcess)
	{
		this.dataProcess = dataProcess;
	}

	public Double getValor()
	{
		return valor;
	}

	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	public List<Advogado> getAdvogadoLIst()
	{
		return advogadoLIst;
	}

	public void setAdvogadoLIst(List<Advogado> advogadoLIst)
	{
		this.advogadoLIst = advogadoLIst;
	}

	public List<Cliente> getClienteList()
	{
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList)
	{
		this.clienteList = clienteList;
	}

	public List<Audiencia> getAudienciaList()
	{
		return audienciaList;
	}

	public void setAudienciaList(List<Audiencia> audienciaList)
	{
		this.audienciaList = audienciaList;
	}

	public List<ProcessoStatus> getProcessoStatusList()
	{
		return processoStatusList;
	}

	public void setProcessoStatusList(List<ProcessoStatus> processoStatusList)
	{
		this.processoStatusList = processoStatusList;
	}

	@Override
	public String toString()
	{
		return "Processo [getId()=" + getId() + ", getDataProcess()=" + getDataProcess() + ", getValor()=" + getValor()
				+ ", getAdvogadoLIst()=" + getAdvogadoLIst() + ", getClienteList()=" + getClienteList()
				+ ", getAudienciaList()=" + getAudienciaList() + ", getProcessoStatusList()=" + getProcessoStatusList()
				+ ", toString()=" + super.toString() + "]";
	}

}
