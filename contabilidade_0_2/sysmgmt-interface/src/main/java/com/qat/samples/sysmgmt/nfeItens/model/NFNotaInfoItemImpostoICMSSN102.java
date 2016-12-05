/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMSSN102 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMSSN102. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMSSN102. */
	private DoisValores origem;

	/**
	 * The econtabil situacaoOperacaoSN for the NFNotaInfoItemImpostoICMSSN102.
	 */
	private DoisValores situacaoOperacaoSN;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMSSN102() {
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

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMSSN102 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoOperacaoSN()=" + getSituacaoOperacaoSN() + ", toString()=" + super.toString() + "]";
	}

}
