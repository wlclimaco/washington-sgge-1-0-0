/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS20 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS20. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS20. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS20. */
	private DoisValores situacaoTributaria;

	/** The econtabil modalidadeBCICMS for the NFNotaInfoItemImpostoICMS20. */
	private DoisValores modalidadeBCICMS;

	/**
	 * The econtabil percentualReducaoBC for the NFNotaInfoItemImpostoICMS20.
	 */
	private String percentualReducaoBC;

	/** The econtabil valorBCICMS for the NFNotaInfoItemImpostoICMS20. */
	private String valorBCICMS;

	/** The econtabil percentualAliquota for the NFNotaInfoItemImpostoICMS20. */
	private String percentualAliquota;

	/** The econtabil valorTributo for the NFNotaInfoItemImpostoICMS20. */
	private String valorTributo;

	/**
	 * The econtabil valorICMSDesoneracao for the NFNotaInfoItemImpostoICMS20.
	 */
	private String valorICMSDesoneracao;

	/** The econtabil desoneracao for the NFNotaInfoItemImpostoICMS20. */
	private DoisValores desoneracao;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS20() {
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
	 * Gets the percentualAliquota.
	 *
	 * @return the percentualAliquota
	 */
	/**
	 * Gets the percentualAliquota.
	 *
	 * @return the percentualAliquota
	 */
	public String getPercentualAliquota() {
		return percentualAliquota;
	}

	/**
	 * Sets the percentualaliquota.
	 *
	 * @param id
	 *            the percentualaliquota to set
	 */
	public void setPercentualAliquota(String percentualaliquota) {
		this.percentualAliquota = percentualaliquota;
	}

	/**
	 * Gets the valorTributo.
	 *
	 * @return the valorTributo
	 */
	/**
	 * Gets the valorTributo.
	 *
	 * @return the valorTributo
	 */
	public String getValorTributo() {
		return valorTributo;
	}

	/**
	 * Sets the valortributo.
	 *
	 * @param id
	 *            the valortributo to set
	 */
	public void setValorTributo(String valortributo) {
		this.valorTributo = valortributo;
	}

	/**
	 * Gets the valorICMSDesoneracao.
	 *
	 * @return the valorICMSDesoneracao
	 */
	/**
	 * Gets the valorICMSDesoneracao.
	 *
	 * @return the valorICMSDesoneracao
	 */
	public String getValorICMSDesoneracao() {
		return valorICMSDesoneracao;
	}

	/**
	 * Sets the valoricmsdesoneracao.
	 *
	 * @param id
	 *            the valoricmsdesoneracao to set
	 */
	public void setValorICMSDesoneracao(String valoricmsdesoneracao) {
		this.valorICMSDesoneracao = valoricmsdesoneracao;
	}

	/**
	 * Gets the desoneracao.
	 *
	 * @return the desoneracao
	 */
	/**
	 * Gets the desoneracao.
	 *
	 * @return the desoneracao
	 */
	public DoisValores getDesoneracao() {
		return desoneracao;
	}

	/**
	 * Sets the desoneracao.
	 *
	 * @param id
	 *            the desoneracao to set
	 */
	public void setDesoneracao(DoisValores desoneracao) {
		this.desoneracao = desoneracao;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMS20 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getModalidadeBCICMS()="
				+ getModalidadeBCICMS() + ", getPercentualReducaoBC()=" + getPercentualReducaoBC()
				+ ", getValorBCICMS()=" + getValorBCICMS() + ", getPercentualAliquota()=" + getPercentualAliquota()
				+ ", getValorTributo()=" + getValorTributo() + ", getValorICMSDesoneracao()="
				+ getValorICMSDesoneracao() + ", getDesoneracao()=" + getDesoneracao() + ", toString()="
				+ super.toString() + "]";
	}

}
