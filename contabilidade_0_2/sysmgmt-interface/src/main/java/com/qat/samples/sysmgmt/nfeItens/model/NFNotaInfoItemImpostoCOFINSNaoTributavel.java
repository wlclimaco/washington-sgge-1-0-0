/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoCOFINSNaoTributavel extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoCOFINSNaoTributavel. */
	private Integer id;

	/**
	 * The econtabil situacaoTributaria for the
	 * NFNotaInfoItemImpostoCOFINSNaoTributavel.
	 */
	private DoisValores situacaoTributaria;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoCOFINSNaoTributavel() {
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

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoCOFINSNaoTributavel [getId()=" + getId() + ", getSituacaoTributaria()="
				+ getSituacaoTributaria() + ", toString()=" + super.toString() + "]";
	}

}
