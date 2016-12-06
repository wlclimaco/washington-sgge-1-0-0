/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao extends ModelCosmeDamiao {

	/**
	 * The econtabil id for the NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.
	 */
	private Integer id;

	/**
	 * The econtabil numero for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.
	 */
	private Integer numero;

	/**
	 * The econtabil sequencial for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.
	 */
	private Integer sequencial;

	/**
	 * The econtabil codigoFabricante for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.
	 */
	private String codigoFabricante;

	/**
	 * The econtabil desconto for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.
	 */
	private String desconto;

	/**
	 * The econtabil numeroAtoConcessorioDrawback for the
	 * NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao.
	 */
	private Long numeroAtoConcessorioDrawback;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao() {
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
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param id
	 *            the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Gets the sequencial.
	 *
	 * @return the sequencial
	 */
	/**
	 * Gets the sequencial.
	 *
	 * @return the sequencial
	 */
	public Integer getSequencial() {
		return sequencial;
	}

	/**
	 * Sets the sequencial.
	 *
	 * @param id
	 *            the sequencial to set
	 */
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	/**
	 * Gets the codigoFabricante.
	 *
	 * @return the codigoFabricante
	 */
	/**
	 * Gets the codigoFabricante.
	 *
	 * @return the codigoFabricante
	 */
	public String getCodigoFabricante() {
		return codigoFabricante;
	}

	/**
	 * Sets the codigofabricante.
	 *
	 * @param id
	 *            the codigofabricante to set
	 */
	public void setCodigoFabricante(String codigofabricante) {
		this.codigoFabricante = codigofabricante;
	}

	/**
	 * Gets the desconto.
	 *
	 * @return the desconto
	 */
	/**
	 * Gets the desconto.
	 *
	 * @return the desconto
	 */
	public String getDesconto() {
		return desconto;
	}

	/**
	 * Sets the desconto.
	 *
	 * @param id
	 *            the desconto to set
	 */
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	/**
	 * Gets the numeroAtoConcessorioDrawback.
	 *
	 * @return the numeroAtoConcessorioDrawback
	 */
	/**
	 * Gets the numeroAtoConcessorioDrawback.
	 *
	 * @return the numeroAtoConcessorioDrawback
	 */
	public Long getNumeroAtoConcessorioDrawback() {
		return numeroAtoConcessorioDrawback;
	}

	/**
	 * Sets the numeroatoconcessoriodrawback.
	 *
	 * @param id
	 *            the numeroatoconcessoriodrawback to set
	 */
	public void setNumeroAtoConcessorioDrawback(Long numeroatoconcessoriodrawback) {
		this.numeroAtoConcessorioDrawback = numeroatoconcessoriodrawback;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoDeclaracaoImportacaoAdicao [getId()=" + getId() + ", getNumero()=" + getNumero()
				+ ", getSequencial()=" + getSequencial() + ", getCodigoFabricante()=" + getCodigoFabricante()
				+ ", getDesconto()=" + getDesconto() + ", getNumeroAtoConcessorioDrawback()="
				+ getNumeroAtoConcessorioDrawback() + ", toString()=" + super.toString() + "]";
	}

}
