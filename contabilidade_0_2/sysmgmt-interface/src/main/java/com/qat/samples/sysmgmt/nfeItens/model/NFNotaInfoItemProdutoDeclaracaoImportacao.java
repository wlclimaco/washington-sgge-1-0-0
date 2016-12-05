/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoDeclaracaoImportacao extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProdutoDeclaracaoImportacao. */
	private Integer id;

	/**
	 * The econtabil numeroRegistro for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private String numeroRegistro;

	/**
	 * The econtabil dataRegistro for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private Long dataRegistro;

	/**
	 * The econtabil localDesembaraco for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private String localDesembaraco;

	/**
	 * The econtabil ufDesembaraco for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private String ufDesembaraco;

	/**
	 * The econtabil dataDesembaraco for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private Long dataDesembaraco;

	/**
	 * The econtabil transporteInternacional for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private DoisValores transporteInternacional;

	/**
	 * The econtabil valorAFRMM for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private String valorAFRMM;

	/**
	 * The econtabil formaImportacaoIntermediacao for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private DoisValores formaImportacaoIntermediacao;

	/** The econtabil cnpj for the NFNotaInfoItemProdutoDeclaracaoImportacao. */
	private String cnpj;

	/**
	 * The econtabil ufTerceiro for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private String ufTerceiro;

	/**
	 * The econtabil codigoExportador for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private String codigoExportador;

	/**
	 * The econtabil adicoes for the NFNotaInfoItemProdutoDeclaracaoImportacao.
	 */
	private List<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> adicoes;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoDeclaracaoImportacao() {
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the numeroRegistro.
	 *
	 * @return the numeroRegistro
	 */
	/**
	 * Gets the numeroRegistro.
	 *
	 * @return the numeroRegistro
	 */
	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	/**
	 * Sets the numeroregistro.
	 *
	 * @param id
	 *            the numeroregistro to set
	 */
	public void setNumeroRegistro(String numeroregistro) {
		this.numeroRegistro = numeroregistro;
	}

	/**
	 * Gets the dataRegistro.
	 *
	 * @return the dataRegistro
	 */
	/**
	 * Gets the dataRegistro.
	 *
	 * @return the dataRegistro
	 */
	public Long getDataRegistro() {
		return dataRegistro;
	}

	/**
	 * Sets the dataregistro.
	 *
	 * @param id
	 *            the dataregistro to set
	 */
	public void setDataRegistro(Long dataregistro) {
		this.dataRegistro = dataregistro;
	}

	/**
	 * Gets the localDesembaraco.
	 *
	 * @return the localDesembaraco
	 */
	/**
	 * Gets the localDesembaraco.
	 *
	 * @return the localDesembaraco
	 */
	public String getLocalDesembaraco() {
		return localDesembaraco;
	}

	/**
	 * Sets the localdesembaraco.
	 *
	 * @param id
	 *            the localdesembaraco to set
	 */
	public void setLocalDesembaraco(String localdesembaraco) {
		this.localDesembaraco = localdesembaraco;
	}

	/**
	 * Gets the ufDesembaraco.
	 *
	 * @return the ufDesembaraco
	 */
	/**
	 * Gets the ufDesembaraco.
	 *
	 * @return the ufDesembaraco
	 */
	public String getUfDesembaraco() {
		return ufDesembaraco;
	}

	/**
	 * Sets the ufdesembaraco.
	 *
	 * @param id
	 *            the ufdesembaraco to set
	 */
	public void setUfDesembaraco(String ufdesembaraco) {
		this.ufDesembaraco = ufdesembaraco;
	}

	/**
	 * Gets the dataDesembaraco.
	 *
	 * @return the dataDesembaraco
	 */
	/**
	 * Gets the dataDesembaraco.
	 *
	 * @return the dataDesembaraco
	 */
	public Long getDataDesembaraco() {
		return dataDesembaraco;
	}

	/**
	 * Sets the datadesembaraco.
	 *
	 * @param id
	 *            the datadesembaraco to set
	 */
	public void setDataDesembaraco(Long datadesembaraco) {
		this.dataDesembaraco = datadesembaraco;
	}

	/**
	 * Gets the transporteInternacional.
	 *
	 * @return the transporteInternacional
	 */
	/**
	 * Gets the transporteInternacional.
	 *
	 * @return the transporteInternacional
	 */
	public DoisValores getTransporteInternacional() {
		return transporteInternacional;
	}

	/**
	 * Sets the transporteinternacional.
	 *
	 * @param id
	 *            the transporteinternacional to set
	 */
	public void setTransporteInternacional(DoisValores transporteinternacional) {
		this.transporteInternacional = transporteinternacional;
	}

	/**
	 * Gets the valorAFRMM.
	 *
	 * @return the valorAFRMM
	 */
	/**
	 * Gets the valorAFRMM.
	 *
	 * @return the valorAFRMM
	 */
	public String getValorAFRMM() {
		return valorAFRMM;
	}

	/**
	 * Sets the valorafrmm.
	 *
	 * @param id
	 *            the valorafrmm to set
	 */
	public void setValorAFRMM(String valorafrmm) {
		this.valorAFRMM = valorafrmm;
	}

	/**
	 * Gets the formaImportacaoIntermediacao.
	 *
	 * @return the formaImportacaoIntermediacao
	 */
	/**
	 * Gets the formaImportacaoIntermediacao.
	 *
	 * @return the formaImportacaoIntermediacao
	 */
	public DoisValores getFormaImportacaoIntermediacao() {
		return formaImportacaoIntermediacao;
	}

	/**
	 * Sets the formaimportacaointermediacao.
	 *
	 * @param id
	 *            the formaimportacaointermediacao to set
	 */
	public void setFormaImportacaoIntermediacao(DoisValores formaimportacaointermediacao) {
		this.formaImportacaoIntermediacao = formaimportacaointermediacao;
	}

	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param id
	 *            the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Gets the ufTerceiro.
	 *
	 * @return the ufTerceiro
	 */
	/**
	 * Gets the ufTerceiro.
	 *
	 * @return the ufTerceiro
	 */
	public String getUfTerceiro() {
		return ufTerceiro;
	}

	/**
	 * Sets the ufterceiro.
	 *
	 * @param id
	 *            the ufterceiro to set
	 */
	public void setUfTerceiro(String ufterceiro) {
		this.ufTerceiro = ufterceiro;
	}

	/**
	 * Gets the codigoExportador.
	 *
	 * @return the codigoExportador
	 */
	/**
	 * Gets the codigoExportador.
	 *
	 * @return the codigoExportador
	 */
	public String getCodigoExportador() {
		return codigoExportador;
	}

	/**
	 * Sets the codigoexportador.
	 *
	 * @param id
	 *            the codigoexportador to set
	 */
	public void setCodigoExportador(String codigoexportador) {
		this.codigoExportador = codigoexportador;
	}

	/**
	 * Gets the adicoes.
	 *
	 * @return the adicoes
	 */
	/**
	 * Gets the adicoes.
	 *
	 * @return the adicoes
	 */
	public List<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> getAdicoes() {
		return adicoes;
	}

	/**
	 * Sets the adicoes.
	 *
	 * @param id
	 *            the adicoes to set
	 */
	public void setAdicoes(List<NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao> adicoes) {
		this.adicoes = adicoes;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoDeclaracaoImportacao [getId()=" + getId() + ", getNumeroRegistro()="
				+ getNumeroRegistro() + ", getDataRegistro()=" + getDataRegistro() + ", getLocalDesembaraco()="
				+ getLocalDesembaraco() + ", getUfDesembaraco()=" + getUfDesembaraco() + ", getDataDesembaraco()="
				+ getDataDesembaraco() + ", getValorAFRMM()=" + getValorAFRMM() + ", getCnpj()=" + getCnpj()
				+ ", getUfTerceiro()=" + getUfTerceiro() + ", getCodigoExportador()=" + getCodigoExportador()
				+ ", toString()=" + super.toString() + "]";
	}

}
