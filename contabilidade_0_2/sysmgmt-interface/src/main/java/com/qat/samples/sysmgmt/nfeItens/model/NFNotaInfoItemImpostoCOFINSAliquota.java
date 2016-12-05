/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoCOFINSAliquota extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoCOFINSAliquota. */
	private Integer id;

	/**
	 * The econtabil situacaoTributaria for the
	 * NFNotaInfoItemImpostoCOFINSAliquota.
	 */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil valorBaseCalulo for the
	 * NFNotaInfoItemImpostoCOFINSAliquota.
	 */
	private String valorBaseCalulo;

	/**
	 * The econtabil percentualAliquota for the
	 * NFNotaInfoItemImpostoCOFINSAliquota.
	 */
	private String percentualAliquota;

	/** The econtabil valor for the NFNotaInfoItemImpostoCOFINSAliquota. */
	private String valor;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoCOFINSAliquota() {
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

	/**
	 * Gets the valorBaseCalulo.
	 *
	 * @return the valorBaseCalulo
	 */
	/**
	 * Gets the valorBaseCalulo.
	 *
	 * @return the valorBaseCalulo
	 */
	public String getValorBaseCalulo() {
		return valorBaseCalulo;
	}

	/**
	 * Sets the valorbasecalulo.
	 *
	 * @param id
	 *            the valorbasecalulo to set
	 */
	public void setValorBaseCalulo(String valorbasecalulo) {
		this.valorBaseCalulo = valorbasecalulo;
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
		return "NFNotaInfoItemImpostoCOFINSAliquota [getId()=" + getId() + ", getSituacaoTributaria()="
				+ getSituacaoTributaria() + ", getValorBaseCalulo()=" + getValorBaseCalulo()
				+ ", getPercentualAliquota()=" + getPercentualAliquota() + ", getValor()=" + getValor()
				+ ", toString()=" + super.toString() + "]";
	}

}
