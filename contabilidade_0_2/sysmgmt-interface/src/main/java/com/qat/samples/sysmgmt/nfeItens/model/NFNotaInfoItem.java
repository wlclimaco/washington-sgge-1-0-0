/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItem extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItem. */
	private Integer id;

	/** The econtabil numeroItem for the NFNotaInfoItem. */
	private Integer numeroItem;

	/** The econtabil produto for the NFNotaInfoItem. */
	private NFNotaInfoItemProduto produto;

	/** The econtabil imposto for the NFNotaInfoItem. */
	private NFNotaInfoItemImposto imposto;

	/** The econtabil impostoDevolvido for the NFNotaInfoItem. */
	private NFImpostoDevolvido impostoDevolvido;

	/** The econtabil informacoesAdicionais for the NFNotaInfoItem. */
	private String informacoesAdicionais;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItem() {
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
	 * Gets the numeroItem.
	 *
	 * @return the numeroItem
	 */
	/**
	 * Gets the numeroItem.
	 *
	 * @return the numeroItem
	 */
	public Integer getNumeroItem() {
		return numeroItem;
	}

	/**
	 * Sets the numeroitem.
	 *
	 * @param id
	 *            the numeroitem to set
	 */
	public void setNumeroItem(Integer numeroitem) {
		this.numeroItem = numeroitem;
	}

	/**
	 * Gets the produto.
	 *
	 * @return the produto
	 */
	/**
	 * Gets the produto.
	 *
	 * @return the produto
	 */
	public NFNotaInfoItemProduto getProduto() {
		return produto;
	}

	/**
	 * Sets the produto.
	 *
	 * @param id
	 *            the produto to set
	 */
	public void setProduto(NFNotaInfoItemProduto produto) {
		this.produto = produto;
	}

	/**
	 * Gets the imposto.
	 *
	 * @return the imposto
	 */
	/**
	 * Gets the imposto.
	 *
	 * @return the imposto
	 */
	public NFNotaInfoItemImposto getImposto() {
		return imposto;
	}

	/**
	 * Sets the imposto.
	 *
	 * @param id
	 *            the imposto to set
	 */
	public void setImposto(NFNotaInfoItemImposto imposto) {
		this.imposto = imposto;
	}

	/**
	 * Gets the impostoDevolvido.
	 *
	 * @return the impostoDevolvido
	 */
	/**
	 * Gets the impostoDevolvido.
	 *
	 * @return the impostoDevolvido
	 */
	public NFImpostoDevolvido getImpostoDevolvido() {
		return impostoDevolvido;
	}

	/**
	 * Sets the impostodevolvido.
	 *
	 * @param id
	 *            the impostodevolvido to set
	 */
	public void setImpostoDevolvido(NFImpostoDevolvido impostodevolvido) {
		this.impostoDevolvido = impostodevolvido;
	}

	/**
	 * Gets the informacoesAdicionais.
	 *
	 * @return the informacoesAdicionais
	 */
	/**
	 * Gets the informacoesAdicionais.
	 *
	 * @return the informacoesAdicionais
	 */
	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	/**
	 * Sets the informacoesadicionais.
	 *
	 * @param id
	 *            the informacoesadicionais to set
	 */
	public void setInformacoesAdicionais(String informacoesadicionais) {
		this.informacoesAdicionais = informacoesadicionais;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItem [getId()=" + getId() + ", getNumeroItem()=" + getNumeroItem()
				+ ", getInformacoesAdicionais()=" + getInformacoesAdicionais() + ", toString()=" + super.toString()
				+ "]";
	}

}
