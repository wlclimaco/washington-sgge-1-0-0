/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS60 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS60. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS60. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS60. */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil valorBCICMSSTRetido for the NFNotaInfoItemImpostoICMS60.
	 */
	private String valorBCICMSSTRetido;

	/** The econtabil valorICMSSTRetido for the NFNotaInfoItemImpostoICMS60. */
	private String valorICMSSTRetido;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS60() {
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
		return "NFNotaInfoItemImpostoICMS60 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getValorBCICMSSTRetido()="
				+ getValorBCICMSSTRetido() + ", getValorICMSSTRetido()=" + getValorICMSSTRetido() + ", toString()="
				+ super.toString() + "]";
	}

}
