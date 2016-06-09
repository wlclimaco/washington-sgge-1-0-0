package com.qat.samples.sysmgmt.advocacia;

import java.util.List;

import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
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
	private Long dataProcess;

	private Long dataFim;

	private ProcessoAcao acao;

	private List<ContasReceber> tituloList;

	private List<Advogado> advogadoList;

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

	public Long getDataProcess()
	{
		return dataProcess;
	}

	public void setDataProcess(Long dataProcess)
	{
		this.dataProcess = dataProcess;
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

	public Long getDataFim() {
		return dataFim;
	}

	public void setDataFim(Long dataFim) {
		this.dataFim = dataFim;
	}

	public ProcessoAcao getAcao() {
		return acao;
	}

	public void setAcao(ProcessoAcao acao) {
		this.acao = acao;
	}

	public List<ContasReceber> getTituloList() {
		return tituloList;
	}

	public void setTituloList(List<ContasReceber> tituloList) {
		this.tituloList = tituloList;
	}

	public List<Advogado> getAdvogadoList() {
		return advogadoList;
	}

	public void setAdvogadoList(List<Advogado> advogadoList) {
		this.advogadoList = advogadoList;
	}

	@Override
	public String toString() {
		return "Processo [getId()=" + getId() + ", getDataProcess()=" + getDataProcess() + ", getClienteList()="
				+ getClienteList() + ", getAudienciaList()=" + getAudienciaList() + ", getProcessoStatusList()="
				+ getProcessoStatusList() + ", getDataFim()=" + getDataFim() + ", getAcao()=" + getAcao()
				+ ", getTituloList()=" + getTituloList() + ", getAdvogadoList()=" + getAdvogadoList() + ", toString()="
				+ super.toString() + "]";
	}

}
