/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMSSN500 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMSSN500. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMSSN500. */
	private DoisValores origem;

	/**
	 * The econtabil situacaoOperacaoSN for the NFNotaInfoItemImpostoICMSSN500.
	 */
	private DoisValores situacaoOperacaoSN;

	/**
	 * The econtabil valorBCICMSSTRetido for the NFNotaInfoItemImpostoICMSSN500.
	 */
	private String valorBCICMSSTRetido;

	/**
	 * The econtabil valorICMSSTRetido for the NFNotaInfoItemImpostoICMSSN500.
	 */
	private String valorICMSSTRetido;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMSSN500() {
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
	 * Gets the valorBCICMSSTRetido.
	 *
	 * @return the valorBCICMSSTRetido
	 */
	/**
	 * Gets the valorBCICMSSTRetido.
	 *
	 * @return the valorBCICMSSTRetido
	 */
	public String getValorBCICMSSTRetido() {
		return valorBCICMSSTRetido;
	}

	/**
	 * Sets the valorbcicmsstretido.
	 *
	 * @param id
	 *            the valorbcicmsstretido to set
	 */
	public void setValorBCICMSSTRetido(String valorbcicmsstretido) {
		this.valorBCICMSSTRetido = valorbcicmsstretido;
	}

	/**
	 * Gets the valorICMSSTRetido.
	 *
	 * @return the valorICMSSTRetido
	 */
	/**
	 * Gets the valorICMSSTRetido.
	 *
	 * @return the valorICMSSTRetido
	 */
	public String getValorICMSSTRetido() {
		return valorICMSSTRetido;
	}

	/**
	 * Sets the valoricmsstretido.
	 *
	 * @param id
	 *            the valoricmsstretido to set
	 */
	public void setValorICMSSTRetido(String valoricmsstretido) {
		this.valorICMSSTRetido = valoricmsstretido;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMSSN500 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoOperacaoSN()=" + getSituacaoOperacaoSN() + ", getValorBCICMSSTRetido()="
				+ getValorBCICMSSTRetido() + ", getValorICMSSTRetido()=" + getValorICMSSTRetido() + ", toString()="
				+ super.toString() + "]";
	}

}
