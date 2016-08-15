package com.qat.samples.sysmgmt.entidade.model;

import java.util.Date;
import java.util.List;

import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.conta.model.ContaCorrente;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ConfiguracaoNFe extends ModelCosmeDamiao
{
	private Integer id;
	private DoisValores presCompr;

	private Integer destConsFinal;

	private Integer preencherDataHora;

	private Double icmsPadrao;

	private Double ipiPadrao;

	private Double pisPadrao;

	private Double cofinsPadrao;

	private DoisValores ambienteEnvio;

	private DoisValores servMsmNota;

	private String serieEnvio;

	private Integer anexarXmlEmail;

	private String idCSC;

	private String cSC;

	private String informacaoAdd;

	private String certificado;

	private String senha;
	private String tokenNFCe;
	
	private String logoDanfe;

	private Integer salvarSenha;

	private Cfop cfopPadrao;


	public ConfiguracaoNFe()
	{
		super();
	}

	public ConfiguracaoNFe(Integer id)
	{
		setId(id);
	}

	public ConfiguracaoNFe(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DoisValores getPresCompr() {
		return presCompr;
	}

	public void setPresCompr(DoisValores presCompr) {
		this.presCompr = presCompr;
	}

	public Integer getDestConsFinal() {
		return destConsFinal;
	}

	public void setDestConsFinal(Integer destConsFinal) {
		this.destConsFinal = destConsFinal;
	}

	public Integer getPreencherDataHora() {
		return preencherDataHora;
	}

	public void setPreencherDataHora(Integer preencherDataHora) {
		this.preencherDataHora = preencherDataHora;
	}

	public Double getIcmsPadrao() {
		return icmsPadrao;
	}

	public void setIcmsPadrao(Double icmsPadrao) {
		this.icmsPadrao = icmsPadrao;
	}

	public Double getIpiPadrao() {
		return ipiPadrao;
	}

	public void setIpiPadrao(Double ipiPadrao) {
		this.ipiPadrao = ipiPadrao;
	}

	public Double getPisPadrao() {
		return pisPadrao;
	}

	public void setPisPadrao(Double pisPadrao) {
		this.pisPadrao = pisPadrao;
	}

	public Double getCofinsPadrao() {
		return cofinsPadrao;
	}

	public void setCofinsPadrao(Double cofinsPadrao) {
		this.cofinsPadrao = cofinsPadrao;
	}

	public DoisValores getAmbienteEnvio() {
		return ambienteEnvio;
	}

	public void setAmbienteEnvio(DoisValores ambienteEnvio) {
		this.ambienteEnvio = ambienteEnvio;
	}

	public DoisValores getServMsmNota() {
		return servMsmNota;
	}

	public void setServMsmNota(DoisValores servMsmNota) {
		this.servMsmNota = servMsmNota;
	}

	public String getSerieEnvio() {
		return serieEnvio;
	}

	public void setSerieEnvio(String serieEnvio) {
		this.serieEnvio = serieEnvio;
	}

	public Integer getAnexarXmlEmail() {
		return anexarXmlEmail;
	}

	public void setAnexarXmlEmail(Integer anexarXmlEmail) {
		this.anexarXmlEmail = anexarXmlEmail;
	}

	public String getIdCSC() {
		return idCSC;
	}

	public void setIdCSC(String idCSC) {
		this.idCSC = idCSC;
	}

	public String getcSC() {
		return cSC;
	}

	public void setcSC(String cSC) {
		this.cSC = cSC;
	}

	public String getInformacaoAdd() {
		return informacaoAdd;
	}

	public void setInformacaoAdd(String informacaoAdd) {
		this.informacaoAdd = informacaoAdd;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getSalvarSenha() {
		return salvarSenha;
	}

	public void setSalvarSenha(Integer salvarSenha) {
		this.salvarSenha = salvarSenha;
	}

	public Cfop getCfopPadrao() {
		return cfopPadrao;
	}

	public void setCfopPadrao(Cfop cfopPadrao) {
		this.cfopPadrao = cfopPadrao;
	}

	public String getTokenNFCe() {
		return tokenNFCe;
	}

	public void setTokenNFCe(String tokenNFCe) {
		this.tokenNFCe = tokenNFCe;
	}

	public String getLogoDanfe() {
		return logoDanfe;
	}

	public void setLogoDanfe(String logoDanfe) {
		this.logoDanfe = logoDanfe;
	}

	@Override
	public String toString() {
		return "ConfiguracaoNFe [getId()=" + getId() + ", getPresCompr()=" + getPresCompr() + ", getDestConsFinal()="
				+ getDestConsFinal() + ", getPreencherDataHora()=" + getPreencherDataHora() + ", getIcmsPadrao()="
				+ getIcmsPadrao() + ", getIpiPadrao()=" + getIpiPadrao() + ", getPisPadrao()=" + getPisPadrao()
				+ ", getCofinsPadrao()=" + getCofinsPadrao() + ", getAmbienteEnvio()=" + getAmbienteEnvio()
				+ ", getServMsmNota()=" + getServMsmNota() + ", getSerieEnvio()=" + getSerieEnvio()
				+ ", getAnexarXmlEmail()=" + getAnexarXmlEmail() + ", getIdCSC()=" + getIdCSC() + ", getcSC()="
				+ getcSC() + ", getInformacaoAdd()=" + getInformacaoAdd() + ", getCertificado()=" + getCertificado()
				+ ", getSenha()=" + getSenha() + ", getSalvarSenha()=" + getSalvarSenha() + ", getCfopPadrao()="
				+ getCfopPadrao() + ", getTokenNFCe()=" + getTokenNFCe() + ", getLogoDanfe()=" + getLogoDanfe()
				+ ", toString()=" + super.toString() + "]";
	}


}
