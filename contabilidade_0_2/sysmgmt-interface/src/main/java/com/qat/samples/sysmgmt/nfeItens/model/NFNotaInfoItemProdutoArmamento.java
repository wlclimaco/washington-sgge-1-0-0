/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoArmamento extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProdutoArmamento. */
	private Integer id;

	/** The econtabil tipo for the NFNotaInfoItemProdutoArmamento. */
	private DoisValores tipo;

	/** The econtabil numeroSerieArma for the NFNotaInfoItemProdutoArmamento. */
	private String numeroSerieArma;

	/** The econtabil numeroSerieCano for the NFNotaInfoItemProdutoArmamento. */
	private String numeroSerieCano;

	/** The econtabil descricao for the NFNotaInfoItemProdutoArmamento. */
	private String descricao;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoArmamento() {
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
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public DoisValores getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param id
	 *            the tipo to set
	 */
	public void setTipo(DoisValores tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the numeroSerieArma.
	 *
	 * @return the numeroSerieArma
	 */
	/**
	 * Gets the numeroSerieArma.
	 *
	 * @return the numeroSerieArma
	 */
	public String getNumeroSerieArma() {
		return numeroSerieArma;
	}

	/**
	 * Sets the numeroseriearma.
	 *
	 * @param id
	 *            the numeroseriearma to set
	 */
	public void setNumeroSerieArma(String numeroseriearma) {
		this.numeroSerieArma = numeroseriearma;
	}

	/**
	 * Gets the numeroSerieCano.
	 *
	 * @return the numeroSerieCano
	 */
	/**
	 * Gets the numeroSerieCano.
	 *
	 * @return the numeroSerieCano
	 */
	public String getNumeroSerieCano() {
		return numeroSerieCano;
	}

	/**
	 * Sets the numeroseriecano.
	 *
	 * @param id
	 *            the numeroseriecano to set
	 */
	public void setNumeroSerieCano(String numeroseriecano) {
		this.numeroSerieCano = numeroseriecano;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param id
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoArmamento [getId()=" + getId() + ", getNumeroSerieArma()=" + getNumeroSerieArma()
				+ ", getNumeroSerieCano()=" + getNumeroSerieCano() + ", getDescricao()=" + getDescricao()
				+ ", toString()=" + super.toString() + "]";
	}

}
