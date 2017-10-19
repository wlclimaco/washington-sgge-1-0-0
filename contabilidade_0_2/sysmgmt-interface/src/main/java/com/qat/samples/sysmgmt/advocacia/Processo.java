package com.qat.samples.sysmgmt.advocacia;

import java.util.List;

import com.qat.samples.sysmgmt.arquivo.model.Arquivo;
import com.qat.samples.sysmgmt.financeiro.model.ContasReceber;
import com.qat.samples.sysmgmt.util.model.Compromisso;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;
import com.qat.samples.sysmgmt.util.model.ParticipanteExterno;

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
	private DoisValores acao;

	private DoisValores natureza;

	private String senha;

	/** The assunto. */
	private String assunto;

	private String porcValorAcao;

	/** The status proc. */
	private DoisValores statusProc;

	private DoisValores grupoTrabalho;

	/** The descricao proc. */
	private String descricaoProc;

	/** The titulo list. */
	private List<ContasReceber> tituloList;

	/** The advogado list. */
	private List<ProcessoCliente> clienteList;

	/** The advogado list. */
	private List<Advogados> advogadoList;

	/** The audiencia list. */
	private List<Compromisso> audienciaList;

	/** The processo status list. */
	private List<ProcessoStatus> processoStatusList;

	/** The envolv list. */
	private List<Envolvidos> envolvList;

	/** The documentos. */
	private List<Arquivo> arquivos;

	/** The envolvidos externo. */
	private List<ParticipanteExterno> envolvidosExterno;

	/** The processo. */
	private String processo;

	/** The situacao. */
	private DoisValores situacao;

	/** The instancia. */
	private DoisValores instancia;

	/** The orgao. */
	private DoisValores orgao;

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

	private Float valorProvisionado;

	private Integer segJustica;

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
	private Integer capautomatica;

	/** The pasta. */
	private String pasta;

	/** The enviar email. */
	private Integer enviarEmail;

	/** The enviar mdg telefone. */
	private Integer enviarMdgTelefone;

	private Integer monitorar;

	/** The quando. */
	private DoisValores quando;

	private String fundamentacaoJuridica;

	private String fatos;

	private String pretensoesCliente;

	private String estrategia;

	private Integer retringirProcesso;

	private List<ProcessoUsuarios> usuariosRestricaoProc;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDataProcess() {
		return dataProcess;
	}

	public void setDataProcess(Long dataProcess) {
		this.dataProcess = dataProcess;
	}

	public Long getDataFim() {
		return dataFim;
	}

	public void setDataFim(Long dataFim) {
		this.dataFim = dataFim;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public DoisValores getAcao() {
		return acao;
	}

	public void setAcao(DoisValores acao) {
		this.acao = acao;
	}

	public DoisValores getNatureza() {
		return natureza;
	}

	public void setNatureza(DoisValores natureza) {
		this.natureza = natureza;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getPorcValorAcao() {
		return porcValorAcao;
	}

	public void setPorcValorAcao(String porcValorAcao) {
		this.porcValorAcao = porcValorAcao;
	}

	public DoisValores getStatusProc() {
		return statusProc;
	}

	public void setStatusProc(DoisValores statusProc) {
		this.statusProc = statusProc;
	}

	public DoisValores getGrupoTrabalho() {
		return grupoTrabalho;
	}

	public void setGrupoTrabalho(DoisValores grupoTrabalho) {
		this.grupoTrabalho = grupoTrabalho;
	}

	public String getDescricaoProc() {
		return descricaoProc;
	}

	public void setDescricaoProc(String descricaoProc) {
		this.descricaoProc = descricaoProc;
	}

	public List<ContasReceber> getTituloList() {
		return tituloList;
	}

	public void setTituloList(List<ContasReceber> tituloList) {
		this.tituloList = tituloList;
	}

	public List<ProcessoCliente> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<ProcessoCliente> clienteList) {
		this.clienteList = clienteList;
	}

	public List<Advogados> getAdvogadoList() {
		return advogadoList;
	}

	public void setAdvogadoList(List<Advogados> advogadoList) {
		this.advogadoList = advogadoList;
	}

	public List<Compromisso> getAudienciaList() {
		return audienciaList;
	}

	public void setAudienciaList(List<Compromisso> audienciaList) {
		this.audienciaList = audienciaList;
	}

	public List<ProcessoStatus> getProcessoStatusList() {
		return processoStatusList;
	}

	public void setProcessoStatusList(List<ProcessoStatus> processoStatusList) {
		this.processoStatusList = processoStatusList;
	}

	public List<Envolvidos> getEnvolvList() {
		return envolvList;
	}

	public void setEnvolvList(List<Envolvidos> envolvList) {
		this.envolvList = envolvList;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public List<ParticipanteExterno> getEnvolvidosExterno() {
		return envolvidosExterno;
	}

	public void setEnvolvidosExterno(List<ParticipanteExterno> envolvidosExterno) {
		this.envolvidosExterno = envolvidosExterno;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public DoisValores getSituacao() {
		return situacao;
	}

	public void setSituacao(DoisValores situacao) {
		this.situacao = situacao;
	}

	public DoisValores getInstancia() {
		return instancia;
	}

	public void setInstancia(DoisValores instancia) {
		this.instancia = instancia;
	}

	public DoisValores getOrgao() {
		return orgao;
	}

	public void setOrgao(DoisValores orgao) {
		this.orgao = orgao;
	}

	public String getNpadraocnj() {
		return npadraocnj;
	}

	public void setNpadraocnj(String npadraocnj) {
		this.npadraocnj = npadraocnj;
	}

	public String getNpadrao() {
		return npadrao;
	}

	public void setNpadrao(String npadrao) {
		this.npadrao = npadrao;
	}

	public Integer getAgendarCap() {
		return agendarCap;
	}

	public void setAgendarCap(Integer agendarCap) {
		this.agendarCap = agendarCap;
	}

	public String getDistribuido() {
		return distribuido;
	}

	public void setDistribuido(String distribuido) {
		this.distribuido = distribuido;
	}

	public Float getValorAcao() {
		return valorAcao;
	}

	public void setValorAcao(Float valorAcao) {
		this.valorAcao = valorAcao;
	}

	public Float getValorProvisionado() {
		return valorProvisionado;
	}

	public void setValorProvisionado(Float valorProvisionado) {
		this.valorProvisionado = valorProvisionado;
	}

	public Integer getSegJustica() {
		return segJustica;
	}

	public void setSegJustica(Integer segJustica) {
		this.segJustica = segJustica;
	}

	public String getObservacaoProc() {
		return observacaoProc;
	}

	public void setObservacaoProc(String observacaoProc) {
		this.observacaoProc = observacaoProc;
	}

	public DoisValores getJustica() {
		return justica;
	}

	public void setJustica(DoisValores justica) {
		this.justica = justica;
	}

	public DoisValores getTribunal() {
		return tribunal;
	}

	public void setTribunal(DoisValores tribunal) {
		this.tribunal = tribunal;
	}

	public DoisValores getInstancia1() {
		return instancia1;
	}

	public void setInstancia1(DoisValores instancia1) {
		this.instancia1 = instancia1;
	}

	public DoisValores getLocalidade() {
		return localidade;
	}

	public void setLocalidade(DoisValores localidade) {
		this.localidade = localidade;
	}

	public DoisValores getCapturpor() {
		return capturpor;
	}

	public void setCapturpor(DoisValores capturpor) {
		this.capturpor = capturpor;
	}

	public String getNumeroprocesso() {
		return numeroprocesso;
	}

	public void setNumeroprocesso(String numeroprocesso) {
		this.numeroprocesso = numeroprocesso;
	}

	public Integer getCapautomatica() {
		return capautomatica;
	}

	public void setCapautomatica(Integer capautomatica) {
		this.capautomatica = capautomatica;
	}

	public String getPasta() {
		return pasta;
	}

	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	public Integer getEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(Integer enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public Integer getEnviarMdgTelefone() {
		return enviarMdgTelefone;
	}

	public void setEnviarMdgTelefone(Integer enviarMdgTelefone) {
		this.enviarMdgTelefone = enviarMdgTelefone;
	}

	public DoisValores getQuando() {
		return quando;
	}

	public void setQuando(DoisValores quando) {
		this.quando = quando;
	}

	public String getFundamentacaoJuridica() {
		return fundamentacaoJuridica;
	}

	public void setFundamentacaoJuridica(String fundamentacaoJuridica) {
		this.fundamentacaoJuridica = fundamentacaoJuridica;
	}

	public String getFatos() {
		return fatos;
	}

	public void setFatos(String fatos) {
		this.fatos = fatos;
	}

	public String getPretensoesCliente() {
		return pretensoesCliente;
	}

	public void setPretensoesCliente(String pretensoesCliente) {
		this.pretensoesCliente = pretensoesCliente;
	}

	public String getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(String estrategia) {
		this.estrategia = estrategia;
	}

	public Integer getRetringirProcesso() {
		return retringirProcesso;
	}

	public void setRetringirProcesso(Integer retringirProcesso) {
		this.retringirProcesso = retringirProcesso;
	}

	public List<ProcessoUsuarios> getUsuariosRestricaoProc() {
		return usuariosRestricaoProc;
	}

	public void setUsuariosRestricaoProc(List<ProcessoUsuarios> usuariosRestricaoProc) {
		this.usuariosRestricaoProc = usuariosRestricaoProc;
	}

	public Integer getMonitorar() {
		return monitorar;
	}

	public void setMonitorar(Integer monitorar) {
		this.monitorar = monitorar;
	}

	@Override
	public String toString() {
		return "Processo [getId()=" + getId() + ", getDataProcess()=" + getDataProcess() + ", getDataFim()="
				+ getDataFim() + ", getValor()=" + getValor() + ", getAcao()=" + getAcao() + ", getNatureza()="
				+ getNatureza() + ", getSenha()=" + getSenha() + ", getAssunto()=" + getAssunto()
				+ ", getPorcValorAcao()=" + getPorcValorAcao() + ", getStatusProc()=" + getStatusProc()
				+ ", getGrupoTrabalho()=" + getGrupoTrabalho() + ", getDescricaoProc()=" + getDescricaoProc()
				+ ", getTituloList()=" + getTituloList() + ", getClienteList()=" + getClienteList()
				+ ", getAdvogadoList()=" + getAdvogadoList() + ", getAudienciaList()=" + getAudienciaList()
				+ ", getProcessoStatusList()=" + getProcessoStatusList() + ", getEnvolvList()=" + getEnvolvList()
				+ ", getArquivos()=" + getArquivos() + ", getEnvolvidosExterno()=" + getEnvolvidosExterno()
				+ ", getProcesso()=" + getProcesso() + ", getSituacao()=" + getSituacao() + ", getInstancia()="
				+ getInstancia() + ", getOrgao()=" + getOrgao() + ", getNpadraocnj()=" + getNpadraocnj()
				+ ", getNpadrao()=" + getNpadrao() + ", getAgendarCap()=" + getAgendarCap() + ", getDistribuido()="
				+ getDistribuido() + ", getValorAcao()=" + getValorAcao() + ", getValorProvisionado()="
				+ getValorProvisionado() + ", getSegJustica()=" + getSegJustica() + ", getObservacaoProc()="
				+ getObservacaoProc() + ", getJustica()=" + getJustica() + ", getTribunal()=" + getTribunal()
				+ ", getInstancia1()=" + getInstancia1() + ", getLocalidade()=" + getLocalidade() + ", getCapturpor()="
				+ getCapturpor() + ", getNumeroprocesso()=" + getNumeroprocesso() + ", getCapautomatica()="
				+ getCapautomatica() + ", getPasta()=" + getPasta() + ", getEnviarEmail()=" + getEnviarEmail()
				+ ", getEnviarMdgTelefone()=" + getEnviarMdgTelefone() + ", getQuando()=" + getQuando()
				+ ", getFundamentacaoJuridica()=" + getFundamentacaoJuridica() + ", getFatos()=" + getFatos()
				+ ", getPretensoesCliente()=" + getPretensoesCliente() + ", getEstrategia()=" + getEstrategia()
				+ ", getRetringirProcesso()=" + getRetringirProcesso() + ", getUsuariosRestricaoProc()="
				+ getUsuariosRestricaoProc() + ", getMonitorar()=" + getMonitorar() + ", toString()=" + super.toString()
				+ "]";
	}


}
