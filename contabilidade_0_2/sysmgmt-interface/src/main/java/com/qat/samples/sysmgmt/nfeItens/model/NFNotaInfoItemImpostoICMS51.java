/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS51 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS51. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS51. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS51. */
	private DoisValores situacaoTributaria;

	/** The econtabil modalidadeBCICMS for the NFNotaInfoItemImpostoICMS51. */
	private DoisValores modalidadeBCICMS;

	/**
	 * The econtabil percentualReducaoBC for the NFNotaInfoItemImpostoICMS51.
	 */
	private String percentualReducaoBC;

	/** The econtabil valorBCICMS for the NFNotaInfoItemImpostoICMS51. */
	private String valorBCICMS;

	/** The econtabil percentualICMS for the NFNotaInfoItemImpostoICMS51. */
	private String percentualICMS;

	/** The econtabil valorICMSOperacao for the NFNotaInfoItemImpostoICMS51. */
	private String valorICMSOperacao;

	/**
	 * The econtabil percentualDiferimento for the NFNotaInfoItemImpostoICMS51.
	 */
	private String percentualDiferimento;

	/**
	 * The econtabil valorICMSDiferimento for the NFNotaInfoItemImpostoICMS51.
	 */
	private String valorICMSDiferimento;

	/** The econtabil valorICMS for the NFNotaInfoItemImpostoICMS51. */
	private String valorICMS;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS51() {
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
	 * Gets the origem.
	 *
	 * @return the origem
	 */
	/**
	 * Gets the origem.
	 *
	 * @return the origem
	 */
	public DoisValores getOrigem() {
		return origem;
	}

	/**
	 * Sets the origem.
	 *
	 * @param id
	 *            the origem to set
	 */
	public void setOrigem(DoisValores origem) {
		this.origem = origem;
	}

	/**
	 * Gets the situacaoTributaria.
	 *
	 * @return the situacaoTributaria
	 */
	/**
	 * Gets the situacaoTributaria.
	 *
	 * @return the situacaoTributaria
	 */
	public DoisValores getSituacaoTributaria() {
		return situacaoTributaria;
	}

	/**
	 * Sets the situacaotributaria.
	 *
	 * @param id
	 *            the situacaotributaria to set
	 */
	public void setSituacaoTributaria(DoisValores situacaotributaria) {
		this.situacaoTributaria = situacaotributaria;
	}

	/**
	 * Gets the modalidadeBCICMS.
	 *
	 * @return the modalidadeBCICMS
	 */
	/**
	 * Gets the modalidadeBCICMS.
	 *
	 * @return the modalidadeBCICMS
	 */
	public DoisValores getModalidadeBCICMS() {
		return modalidadeBCICMS;
	}

	/**
	 * Sets the modalidadebcicms.
	 *
	 * @param id
	 *            the modalidadebcicms to set
	 */
	public void setModalidadeBCICMS(DoisValores modalidadebcicms) {
		this.modalidadeBCICMS = modalidadebcicms;
	}

	/**
	 * Gets the percentualReducaoBC.
	 *
	 * @return the percentualReducaoBC
	 */
	/**
	 * Gets the percentualReducaoBC.
	 *
	 * @return the percentualReducaoBC
	 */
	public String getPercentualReducaoBC() {
		return percentualReducaoBC;
	}

	/**
	 * Sets the percentualreducaobc.
	 *
	 * @param id
	 *            the percentualreducaobc to set
	 */
	public void setPercentualReducaoBC(String percentualreducaobc) {
		this.percentualReducaoBC = percentualreducaobc;
	}

	/**
	 * Gets the valorBCICMS.
	 *
	 * @return the valorBCICMS
	 */
	/**
	 * Gets the valorBCICMS.
	 *
	 * @return the valorBCICMS
	 */
	public String getValorBCICMS() {
		return valorBCICMS;
	}

	/**
	 * Sets the valorbcicms.
	 *
	 * @param id
	 *            the valorbcicms to set
	 */
	public void setValorBCICMS(String valorbcicms) {
		this.valorBCICMS = valorbcicms;
	}

	/**
	 * Gets the percentualICMS.
	 *
	 * @return the percentualICMS
	 */
	/**
	 * Gets the percentualICMS.
	 *
	 * @return the percentualICMS
	 */
	public String getPercentualICMS() {
		return percentualICMS;
	}

	/**
	 * Sets the percentualicms.
	 *
	 * @param id
	 *            the percentualicms to set
	 */
	public void setPercentualICMS(String percentualicms) {
		this.percentualICMS = percentualicms;
	}

	/**
	 * Gets the valorICMSOperacao.
	 *
	 * @return the valorICMSOperacao
	 */
	/**
	 * Gets the valorICMSOperacao.
	 *
	 * @return the valorICMSOperacao
	 */
	public String getValorICMSOperacao() {
		return valorICMSOperacao;
	}

	/**
	 * Sets the valoricmsoperacao.
	 *
	 * @param id
	 *            the valoricmsoperacao to set
	 */
	public void setValorICMSOperacao(String valoricmsoperacao) {
		this.valorICMSOperacao = valoricmsoperacao;
	}

	/**
	 * Gets the percentualDiferimento.
	 *
	 * @return the percentualDiferimento
	 */
	/**
	 * Gets the percentualDiferimento.
	 *
	 * @return the percentualDiferimento
	 */
	public String getPercentualDiferimento() {
		return percentualDiferimento;
	}

	/**
	 * Sets the percentualdiferimento.
	 *
	 * @param id
	 *            the percentualdiferimento to set
	 */
	public void setPercentualDiferimento(String percentualdiferimento) {
		this.percentualDiferimento = percentualdiferimento;
	}

	/**
	 * Gets the valorICMSDiferimento.
	 *
	 * @return the valorICMSDiferimento
	 */
	/**
	 * Gets the valorICMSDiferimento.
	 *
	 * @return the valorICMSDiferimento
	 */
	public String getValorICMSDiferimento() {
		return valorICMSDiferimento;
	}

	/**
	 * Sets the valoricmsdiferimento.
	 *
	 * @param id
	 *            the valoricmsdiferimento to set
	 */
	public void setValorICMSDiferimento(String valoricmsdiferimento) {
		this.valorICMSDiferimento = valoricmsdiferimento;
	}

	/**
	 * Gets the valorICMS.
	 *
	 * @return the valorICMS
	 */
	/**
	 * Gets the valorICMS.
	 *
	 * @return the valorICMS
	 */
	public String getValorICMS() {
		return valorICMS;
	}

	/**
	 * Sets the valoricms.
	 *
	 * @param id
	 *            the valoricms to set
	 */
	public void setValorICMS(String valoricms) {
		this.valorICMS = valoricms;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMS51 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getModalidadeBCICMS()="
				+ getModalidadeBCICMS() + ", getPercentualReducaoBC()=" + getPercentualReducaoBC()
				+ ", getValorBCICMS()=" + getValorBCICMS() + ", getPercentualICMS()=" + getPercentualICMS()
				+ ", getValorICMSOperacao()=" + getValorICMSOperacao() + ", getPercentualDiferimento()="
				+ getPercentualDiferimento() + ", getValorICMSDiferimento()=" + getValorICMSDiferimento()
				+ ", getValorICMS()=" + getValorICMS() + ", toString()=" + super.toString() + "]";
	}

}
