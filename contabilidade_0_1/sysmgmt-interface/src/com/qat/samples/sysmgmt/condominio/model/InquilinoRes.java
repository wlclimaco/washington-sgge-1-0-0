package com.qat.samples.sysmgmt.advocacia.model;

import java.util.List;

import com.qat.samples.sysmgmt.pessoa.Cliente;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class InquilinoRes extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

		private String bloco;
	private String apartamento;

	public InquilinoRes()
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

	public List<InquilinoResStatus> getInquilinoResStatusList()
	{
		return processoStatusList;
	}

	public void setInquilinoResStatusList(List<InquilinoResStatus> processoStatusList)
	{
		this.processoStatusList = processoStatusList;
	}

	@Override
	public String toString()
	{
		return "InquilinoRes [getId()=" + getId() + ", getDataProcess()=" + getDataProcess() + ", getValor()=" + getValor()
				+ ", getAdvogadoLIst()=" + getAdvogadoLIst() + ", getClienteList()=" + getClienteList()
				+ ", getAudienciaList()=" + getAudienciaList() + ", getInquilinoResStatusList()=" + getInquilinoResStatusList()
				+ ", toString()=" + super.toString() + "]";
	}

}
