/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS40 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS40. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS40. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS40. */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil valorICMSDesoneracao for the NFNotaInfoItemImpostoICMS40.
	 */
	private String valorICMSDesoneracao;

	/**
	 * The econtabil motivoDesoneracaoICMS for the NFNotaInfoItemImpostoICMS40.
	 */
	private DoisValores motivoDesoneracaoICMS;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS40() {
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
	 * Gets the motivoDesoneracaoICMS.
	 *
	 * @return the motivoDesoneracaoICMS
	 */
	/**
	 * Gets the motivoDesoneracaoICMS.
	 *
	 * @return the motivoDesoneracaoICMS
	 */
	public DoisValores getMotivoDesoneracaoICMS() {
		return motivoDesoneracaoICMS;
	}

	/**
	 * Sets the motivodesoneracaoicms.
	 *
	 * @param id
	 *            the motivodesoneracaoicms to set
	 */
	public void setMotivoDesoneracaoICMS(DoisValores motivodesoneracaoicms) {
		this.motivoDesoneracaoICMS = motivodesoneracaoicms;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMS40 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getValorICMSDesoneracao()="
				+ getValorICMSDesoneracao() + ", getMotivoDesoneracaoICMS()=" + getMotivoDesoneracaoICMS()
				+ ", toString()=" + super.toString() + "]";
	}

}
