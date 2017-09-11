package com.qat.samples.sysmgmt.advocacia;

import java.util.List;

import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */
@SuppressWarnings("serial")
public class Processo extends ModelCosmeDamiao {
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Long dataProcess;

	/** The data fim. */
	private Long dataFim;

	/** The valor. */
	private Double valor;

	/** The acao. */
	private ProcessoAcao acao;

	/** The titulo list. */
	private List<ContasReceber> tituloList;

	/** The advogado list. */
	private List<Advogado> advogadoList;

	/** The audiencia list. */
	private List<Audiencia> audienciaList;

	/** The processo status list. */
	private List<ProcessoStatus> processoStatusList;

	/** The assunto. */
	private String assunto;

	/** The status proc. */
	private DoisValores statusProc;

	/** The descricao proc. */
	private String descricaoProc;

	/** The envolv list. */
	private List<Envolvidos> envolvList;

	/** The processo. */
	private String processo;

	/** The situacao. */
	private DoisValores situacao;

	/** The instancia. */
	private DoisValores instancia;

	/** The orgao. */
	private String orgao;

	/** The npadraocnj. */
	private String npadraocnj;

	/** The npadrao. */
	private String npadrao;

	/** The agendar cap. */
	private Integer agendarCap;

	/** The distribuido. */
	private String distribuido;

	/** The valor acao. */
	private Float valorAcao;

	/** The observacao proc. */
	private String observacaoProc;

	/** The justica. */
	private DoisValores justica;

	/** The tribunal. */
	private DoisValores tribunal;

	/** The instancia 1. */
	private DoisValores instancia1;

	/** The localidade. */
	private DoisValores localidade;

	/** The capturpor. */
	private DoisValores capturpor;

	/** The numeroprocesso. */
	private String numeroprocesso;

	/** The capautomatica. */
	private DoisValores capautomatica;

	/** The pasta. */
	private String pasta;

	/**
	 * Instantiates a new processo.
	 */
	public Processo() {
		super();

	}

	/**
	 * Instantiates a new processo.
	 *
	 * @param i
	 *            the i
	 * @param string
	 *            the string
	 */
	public Processo(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the data process.
	 *
	 * @return the data process
	 */
	public Long getDataProcess() {
		return dataProcess;
	}

	/**
	 * Sets the data process.
	 *
	 * @param dataProcess
	 *            the new data process
	 */
	public void setDataProcess(Long dataProcess) {
		this.dataProcess = dataProcess;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param valor
	 *            the new valor
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * Gets the data fim.
	 *
	 * @return the data fim
	 */
	public Long getDataFim() {
		return dataFim;
	}

	/**
	 * Sets the data fim.
	 *
	 * @param dataFim
	 *            the new data fim
	 */
	public void setDataFim(Long dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * Gets the acao.
	 *
	 * @return the acao
	 */
	public ProcessoAcao getAcao() {
		return acao;
	}

	/**
	 * Sets the acao.
	 *
	 * @param acao
	 *            the new acao
	 */
	public void setAcao(ProcessoAcao acao) {
		this.acao = acao;
	}

	/**
	 * Gets the titulo list.
	 *
	 * @return the titulo list
	 */
	public List<ContasReceber> getTituloList() {
		return tituloList;
	}

	/**
	 * Sets the titulo list.
	 *
	 * @param tituloList
	 *            the new titulo list
	 */
	public void setTituloList(List<ContasReceber> tituloList) {
		this.tituloList = tituloList;
	}

	/**
	 * Gets the advogado list.
	 *
	 * @return the advogado list
	 */
	public List<Advogado> getAdvogadoList() {
		return advogadoList;
	}

	/**
	 * Sets the advogado list.
	 *
	 * @param advogadoList
	 *            the new advogado list
	 */
	public void setAdvogadoList(List<Advogado> advogadoList) {
		this.advogadoList = advogadoList;
	}

	/**
	 * Gets the audiencia list.
	 *
	 * @return the audiencia list
	 */
	public List<Audiencia> getAudienciaList() {
		return audienciaList;
	}

	/**
	 * Sets the audiencia list.
	 *
	 * @param audienciaList
	 *            the new audiencia list
	 */
	public void setAudienciaList(List<Audiencia> audienciaList) {
		this.audienciaList = audienciaList;
	}

	/**
	 * Gets the processo status list.
	 *
	 * @return the processo status list
	 */
	public List<ProcessoStatus> getProcessoStatusList() {
		return processoStatusList;
	}

	/**
	 * Sets the processo status list.
	 *
	 * @param processoStatusList
	 *            the new processo status list
	 */
	public void setProcessoStatusList(List<ProcessoStatus> processoStatusList) {
		this.processoStatusList = processoStatusList;
	}

	/**
	 * Gets the assunto.
	 *
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * Sets the assunto.
	 *
	 * @param assunto
	 *            the new assunto
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * Gets the status proc.
	 *
	 * @return the status proc
	 */
	public DoisValores getStatusProc() {
		return statusProc;
	}

	/**
	 * Sets the status proc.
	 *
	 * @param statusProc
	 *            the new status proc
	 */
	public void setStatusProc(DoisValores statusProc) {
		this.statusProc = statusProc;
	}

	/**
	 * Gets the descricao proc.
	 *
	 * @return the descricao proc
	 */
	public String getDescricaoProc() {
		return descricaoProc;
	}

	/**
	 * Sets the descricao proc.
	 *
	 * @param descricaoProc
	 *            the new descricao proc
	 */
	public void setDescricaoProc(String descricaoProc) {
		this.descricaoProc = descricaoProc;
	}

	/**
	 * Gets the envolv list.
	 *
	 * @return the envolv list
	 */
	public List<Envolvidos> getEnvolvList() {
		return envolvList;
	}

	/**
	 * Sets the envolv list.
	 *
	 * @param envolvList
	 *            the new envolv list
	 */
	public void setEnvolvList(List<Envolvidos> envolvList) {
		this.envolvList = envolvList;
	}

	/**
	 * Gets the processo.
	 *
	 * @return the processo
	 */
	public String getProcesso() {
		return processo;
	}

	/**
	 * Sets the processo.
	 *
	 * @param processo
	 *            the new processo
	 */
	public void setProcesso(String processo) {
		this.processo = processo;
	}

	/**
	 * Gets the situacao.
	 *
	 * @return the situacao
	 */
	public DoisValores getSituacao() {
		return situacao;
	}

	/**
	 * Sets the situacao.
	 *
	 * @param situacao
	 *            the new situacao
	 */
	public void setSituacao(DoisValores situacao) {
		this.situacao = situacao;
	}

	/**
	 * Gets the instancia.
	 *
	 * @return the instancia
	 */
	public DoisValores getInstancia() {
		return instancia;
	}

	/**
	 * Sets the instancia.
	 *
	 * @param instancia
	 *            the new instancia
	 */
	public void setInstancia(DoisValores instancia) {
		this.instancia = instancia;
	}

	/**
	 * Gets the orgao.
	 *
	 * @return the orgao
	 */
	public String getOrgao() {
		return orgao;
	}

	/**
	 * Sets the orgao.
	 *
	 * @param orgao
	 *            the new orgao
	 */
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	/**
	 * Gets the npadraocnj.
	 *
	 * @return the npadraocnj
	 */
	public String getNpadraocnj() {
		return npadraocnj;
	}

	/**
	 * Sets the npadraocnj.
	 *
	 * @param npadraocnj
	 *            the new npadraocnj
	 */
	public void setNpadraocnj(String npadraocnj) {
		this.npadraocnj = npadraocnj;
	}

	/**
	 * Gets the npadrao.
	 *
	 * @return the npadrao
	 */
	public String getNpadrao() {
		return npadrao;
	}

	/**
	 * Sets the npadrao.
	 *
	 * @param npadrao
	 *            the new npadrao
	 */
	public void setNpadrao(String npadrao) {
		this.npadrao = npadrao;
	}

	/**
	 * Gets the agendar cap.
	 *
	 * @return the agendar cap
	 */
	public Integer getAgendarCap() {
		return agendarCap;
	}

	/**
	 * Sets the agendar cap.
	 *
	 * @param agendarCap
	 *            the new agendar cap
	 */
	public void setAgendarCap(Integer agendarCap) {
		this.agendarCap = agendarCap;
	}

	/**
	 * Gets the distribuido.
	 *
	 * @return the distribuido
	 */
	public String getDistribuido() {
		return distribuido;
	}

	/**
	 * Sets the distribuido.
	 *
	 * @param distribuido
	 *            the new distribuido
	 */
	public void setDistribuido(String distribuido) {
		this.distribuido = distribuido;
	}

	/**
	 * Gets the valor acao.
	 *
	 * @return the valor acao
	 */
	public Float getValorAcao() {
		return valorAcao;
	}

	/**
	 * Sets the valor acao.
	 *
	 * @param valorAcao
	 *            the new valor acao
	 */
	public void setValorAcao(Float valorAcao) {
		this.valorAcao = valorAcao;
	}

	/**
	 * Gets the observacao proc.
	 *
	 * @return the observacao proc
	 */
	public String getObservacaoProc() {
		return observacaoProc;
	}

	/**
	 * Sets the observacao proc.
	 *
	 * @param observacaoProc
	 *            the new observacao proc
	 */
	public void setObservacaoProc(String observacaoProc) {
		this.observacaoProc = observacaoProc;
	}

	/**
	 * Gets the justica.
	 *
	 * @return the justica
	 */
	public DoisValores getJustica() {
		return justica;
	}

	/**
	 * Sets the justica.
	 *
	 * @param justica
	 *            the new justica
	 */
	public void setJustica(DoisValores justica) {
		this.justica = justica;
	}

	/**
	 * Gets the tribunal.
	 *
	 * @return the tribunal
	 */
	public DoisValores getTribunal() {
		return tribunal;
	}

	/**
	 * Sets the tribunal.
	 *
	 * @param tribunal
	 *            the new tribunal
	 */
	public void setTribunal(DoisValores tribunal) {
		this.tribunal = tribunal;
	}

	/**
	 * Gets the instancia 1.
	 *
	 * @return the instancia 1
	 */
	public DoisValores getInstancia1() {
		return instancia1;
	}

	/**
	 * Sets the instancia 1.
	 *
	 * @param instancia1
	 *            the new instancia 1
	 */
	public void setInstancia1(DoisValores instancia1) {
		this.instancia1 = instancia1;
	}

	/**
	 * Gets the localidade.
	 *
	 * @return the localidade
	 */
	public DoisValores getLocalidade() {
		return localidade;
	}

	/**
	 * Sets the localidade.
	 *
	 * @param localidade
	 *            the new localidade
	 */
	public void setLocalidade(DoisValores localidade) {
		this.localidade = localidade;
	}

	/**
	 * Gets the capturpor.
	 *
	 * @return the capturpor
	 */
	public DoisValores getCapturpor() {
		return capturpor;
	}

	/**
	 * Sets the capturpor.
	 *
	 * @param capturpor
	 *            the new capturpor
	 */
	public void setCapturpor(DoisValores capturpor) {
		this.capturpor = capturpor;
	}

	/**
	 * Gets the numeroprocesso.
	 *
	 * @return the numeroprocesso
	 */
	public String getNumeroprocesso() {
		return numeroprocesso;
	}

	/**
	 * Sets the numeroprocesso.
	 *
	 * @param numeroprocesso
	 *            the new numeroprocesso
	 */
	public void setNumeroprocesso(String numeroprocesso) {
		this.numeroprocesso = numeroprocesso;
	}

	/**
	 * Gets the capautomatica.
	 *
	 * @return the capautomatica
	 */
	public DoisValores getCapautomatica() {
		return capautomatica;
	}

	/**
	 * Sets the capautomatica.
	 *
	 * @param capautomatica
	 *            the new capautomatica
	 */
	public void setCapautomatica(DoisValores capautomatica) {
		this.capautomatica = capautomatica;
	}

	/**
	 * Gets the pasta.
	 *
	 * @return the pasta
	 */
	public String getPasta() {
		return pasta;
	}

	/**
	 * Sets the pasta.
	 *
	 * @param pasta
	 *            the new pasta
	 */
	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao#toString()
	 */
	@Override
	public String toString() {
		return "Processo [getId()=" + getId() + ", getDataProcess()=" + getDataProcess() + ", getValor()=" + getValor()
				+ ", getDataFim()=" + getDataFim() + ", getAcao()=" + getAcao() + ", getTituloList()=" + getTituloList()
				+ ", getAdvogadoList()=" + getAdvogadoList() + ", getAudienciaList()=" + getAudienciaList()
				+ ", getProcessoStatusList()=" + getProcessoStatusList() + ", getAssunto()=" + getAssunto()
				+ ", getStatusProc()=" + getStatusProc() + ", getDescricaoProc()=" + getDescricaoProc()
				+ ", getEnvolvList()=" + getEnvolvList() + ", getProcesso()=" + getProcesso() + ", getSituacao()="
				+ getSituacao() + ", getInstancia()=" + getInstancia() + ", getOrgao()=" + getOrgao()
				+ ", getNpadraocnj()=" + getNpadraocnj() + ", getNpadrao()=" + getNpadrao() + ", getAgendarCap()="
				+ getAgendarCap() + ", getDistribuido()=" + getDistribuido() + ", getValorAcao()=" + getValorAcao()
				+ ", getObservacaoProc()=" + getObservacaoProc() + ", getJustica()=" + getJustica() + ", getTribunal()="
				+ getTribunal() + ", getInstancia1()=" + getInstancia1() + ", getLocalidade()=" + getLocalidade()
				+ ", getCapturpor()=" + getCapturpor() + ", getNumeroprocesso()=" + getNumeroprocesso()
				+ ", getCapautomatica()=" + getCapautomatica() + ", getPasta()=" + getPasta() + ", toString()="
				+ super.toString() + "]";
	}

}
