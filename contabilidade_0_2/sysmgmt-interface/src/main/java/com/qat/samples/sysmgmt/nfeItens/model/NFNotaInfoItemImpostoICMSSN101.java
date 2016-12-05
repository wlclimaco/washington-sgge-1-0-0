/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMSSN101 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMSSN101. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMSSN101. */
	private DoisValores origem;

	/**
	 * The econtabil situacaoOperacaoSN for the NFNotaInfoItemImpostoICMSSN101.
	 */
	private DoisValores situacaoOperacaoSN;

	/**
	 * The econtabil percentualAliquotaAplicavelCalculoCreditoSN for the
	 * NFNotaInfoItemImpostoICMSSN101.
	 */
	private String percentualAliquotaAplicavelCalculoCreditoSN;

	/**
	 * The econtabil valorCreditoICMSSN for the NFNotaInfoItemImpostoICMSSN101.
	 */
	private String valorCreditoICMSSN;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMSSN101() {
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
	 * Gets the situacaoOperacaoSN.
	 *
	 * @return the situacaoOperacaoSN
	 */
	/**
	 * Gets the situacaoOperacaoSN.
	 *
	 * @return the situacaoOperacaoSN
	 */
	public DoisValores getSituacaoOperacaoSN() {
		return situacaoOperacaoSN;
	}

	/**
	 * Sets the situacaooperacaosn.
	 *
	 * @param id
	 *            the situacaooperacaosn to set
	 */
	public void setSituacaoOperacaoSN(DoisValores situacaooperacaosn) {
		this.situacaoOperacaoSN = situacaooperacaosn;
	}

	/**
	 * Gets the percentualAliquotaAplicavelCalculoCreditoSN.
	 *
	 * @return the percentualAliquotaAplicavelCalculoCreditoSN
	 */
	/**
	 * Gets the percentualAliquotaAplicavelCalculoCreditoSN.
	 *
	 * @return the percentualAliquotaAplicavelCalculoCreditoSN
	 */
	public String getPercentualAliquotaAplicavelCalculoCreditoSN() {
		return percentualAliquotaAplicavelCalculoCreditoSN;
	}

	/**
	 * Sets the percentualaliquotaaplicavelcalculocreditosn.
	 *
	 * @param id
	 *            the percentualaliquotaaplicavelcalculocreditosn to set
	 */
	public void setPercentualAliquotaAplicavelCalculoCreditoSN(String percentualaliquotaaplicavelcalculocreditosn) {
		this.percentualAliquotaAplicavelCalculoCreditoSN = percentualaliquotaaplicavelcalculocreditosn;
	}

	/**
	 * Gets the valorCreditoICMSSN.
	 *
	 * @return the valorCreditoICMSSN
	 */
	/**
	 * Gets the valorCreditoICMSSN.
	 *
	 * @return the valorCreditoICMSSN
	 */
	public String getValorCreditoICMSSN() {
		return valorCreditoICMSSN;
	}

	/**
	 * Sets the valorcreditoicmssn.
	 *
	 * @param id
	 *            the valorcreditoicmssn to set
	 */
	public void setValorCreditoICMSSN(String valorcreditoicmssn) {
		this.valorCreditoICMSSN = valorcreditoicmssn;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMSSN101 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoOperacaoSN()=" + getSituacaoOperacaoSN()
				+ ", getPercentualAliquotaAplicavelCalculoCreditoSN()="
				+ getPercentualAliquotaAplicavelCalculoCreditoSN() + ", getValorCreditoICMSSN()="
				+ getValorCreditoICMSSN() + ", toString()=" + super.toString() + "]";
	}

}
