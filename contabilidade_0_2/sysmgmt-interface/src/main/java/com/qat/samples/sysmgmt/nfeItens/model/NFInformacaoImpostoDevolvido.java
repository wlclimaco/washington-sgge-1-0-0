/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFInformacaoImpostoDevolvido extends ModelCosmeDamiao {

	/** The econtabil id for the NFInformacaoImpostoDevolvido. */
	private Integer id;

	/** The econtabil valorIPIDevolvido for the NFInformacaoImpostoDevolvido. */
	private String valorIPIDevolvido;

	/**
	 * Default constructor.
	 */
	public NFInformacaoImpostoDevolvido() {
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
	 * Gets the valorIPIDevolvido.
	 *
	 * @return the valorIPIDevolvido
	 */
	/**
	 * Gets the valorIPIDevolvido.
	 *
	 * @return the valorIPIDevolvido
	 */
	public String getValorIPIDevolvido() {
		return valorIPIDevolvido;
	}

	/**
	 * Sets the valoripidevolvido.
	 *
	 * @param id
	 *            the valoripidevolvido to set
	 */
	public void setValorIPIDevolvido(String valoripidevolvido) {
		this.valorIPIDevolvido = valoripidevolvido;
	}

	@Override
	public String toString() {
		return "NFInformacaoImpostoDevolvido [getId()=" + getId() + ", getValorIPIDevolvido()=" + getValorIPIDevolvido()
				+ ", toString()=" + super.toString() + "]";
	}

}
