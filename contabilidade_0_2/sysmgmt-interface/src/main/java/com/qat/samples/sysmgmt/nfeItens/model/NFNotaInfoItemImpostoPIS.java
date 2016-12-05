/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoPIS extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoPIS. */
	private Integer id;

	/** The econtabil aliquota for the NFNotaInfoItemImpostoPIS. */
	private NFNotaInfoItemImpostoPISAliquota aliquota;

	/** The econtabil quantidade for the NFNotaInfoItemImpostoPIS. */
	private NFNotaInfoItemImpostoPISQuantidade quantidade;

	/** The econtabil naoTributado for the NFNotaInfoItemImpostoPIS. */
	private NFNotaInfoItemImpostoPISNaoTributado naoTributado;

	/** The econtabil outrasOperacoes for the NFNotaInfoItemImpostoPIS. */
	private NFNotaInfoItemImpostoPISOutrasOperacoes outrasOperacoes;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoPIS() {
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
	 * Gets the aliquota.
	 *
	 * @return the aliquota
	 */
	/**
	 * Gets the aliquota.
	 *
	 * @return the aliquota
	 */
	public NFNotaInfoItemImpostoPISAliquota getAliquota() {
		return aliquota;
	}

	/**
	 * Sets the aliquota.
	 *
	 * @param id
	 *            the aliquota to set
	 */
	public void setAliquota(NFNotaInfoItemImpostoPISAliquota aliquota) {
		this.aliquota = aliquota;
	}

	/**
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	/**
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	public NFNotaInfoItemImpostoPISQuantidade getQuantidade() {
		return quantidade;
	}

	/**
	 * Sets the quantidade.
	 *
	 * @param id
	 *            the quantidade to set
	 */
	public void setQuantidade(NFNotaInfoItemImpostoPISQuantidade quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Gets the naoTributado.
	 *
	 * @return the naoTributado
	 */
	/**
	 * Gets the naoTributado.
	 *
	 * @return the naoTributado
	 */
	public NFNotaInfoItemImpostoPISNaoTributado getNaoTributado() {
		return naoTributado;
	}

	/**
	 * Sets the naotributado.
	 *
	 * @param id
	 *            the naotributado to set
	 */
	public void setNaoTributado(NFNotaInfoItemImpostoPISNaoTributado naotributado) {
		this.naoTributado = naotributado;
	}

	/**
	 * Gets the outrasOperacoes.
	 *
	 * @return the outrasOperacoes
	 */
	/**
	 * Gets the outrasOperacoes.
	 *
	 * @return the outrasOperacoes
	 */
	public NFNotaInfoItemImpostoPISOutrasOperacoes getOutrasOperacoes() {
		return outrasOperacoes;
	}

	/**
	 * Sets the outrasoperacoes.
	 *
	 * @param id
	 *            the outrasoperacoes to set
	 */
	public void setOutrasOperacoes(NFNotaInfoItemImpostoPISOutrasOperacoes outrasoperacoes) {
		this.outrasOperacoes = outrasoperacoes;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoPIS [getId()=" + getId() + ", getAliquota()=" + getAliquota()
				+ ", getQuantidade()=" + getQuantidade() + ", getNaoTributado()=" + getNaoTributado()
				+ ", getOutrasOperacoes()=" + getOutrasOperacoes() + ", toString()=" + super.toString() + "]";
	}

}
