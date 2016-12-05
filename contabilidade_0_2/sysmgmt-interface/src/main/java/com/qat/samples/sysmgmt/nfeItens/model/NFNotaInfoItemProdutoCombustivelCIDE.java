/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoCombustivelCIDE extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProdutoCombustivelCIDE. */
	private Integer id;

	/**
	 * The econtabil quantidadeBCCIDE for the
	 * NFNotaInfoItemProdutoCombustivelCIDE.
	 */
	private String quantidadeBCCIDE;

	/**
	 * The econtabil valorAliquota for the NFNotaInfoItemProdutoCombustivelCIDE.
	 */
	private String valorAliquota;

	/** The econtabil valor for the NFNotaInfoItemProdutoCombustivelCIDE. */
	private String valor;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoCombustivelCIDE() {
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
	 * Gets the quantidadeBCCIDE.
	 *
	 * @return the quantidadeBCCIDE
	 */
	/**
	 * Gets the quantidadeBCCIDE.
	 *
	 * @return the quantidadeBCCIDE
	 */
	public String getQuantidadeBCCIDE() {
		return quantidadeBCCIDE;
	}

	/**
	 * Sets the quantidadebccide.
	 *
	 * @param id
	 *            the quantidadebccide to set
	 */
	public void setQuantidadeBCCIDE(String quantidadebccide) {
		this.quantidadeBCCIDE = quantidadebccide;
	}

	/**
	 * Gets the valorAliquota.
	 *
	 * @return the valorAliquota
	 */
	/**
	 * Gets the valorAliquota.
	 *
	 * @return the valorAliquota
	 */
	public String getValorAliquota() {
		return valorAliquota;
	}

	/**
	 * Sets the valoraliquota.
	 *
	 * @param id
	 *            the valoraliquota to set
	 */
	public void setValorAliquota(String valoraliquota) {
		this.valorAliquota = valoraliquota;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Sets the valor.
	 *
	 * @param id
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoCombustivelCIDE [getId()=" + getId() + ", getQuantidadeBCCIDE()="
				+ getQuantidadeBCCIDE() + ", getValorAliquota()=" + getValorAliquota() + ", getValor()=" + getValor()
				+ ", toString()=" + super.toString() + "]";
	}

}
