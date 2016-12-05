/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoIPITributado extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoIPITributado. */
	private Integer id;

	/**
	 * The econtabil situacaoTributaria for the
	 * NFNotaInfoItemImpostoIPITributado.
	 */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil valorBaseCalculo for the NFNotaInfoItemImpostoIPITributado.
	 */
	private String valorBaseCalculo;

	/**
	 * The econtabil percentualAliquota for the
	 * NFNotaInfoItemImpostoIPITributado.
	 */
	private String percentualAliquota;

	/** The econtabil quantidade for the NFNotaInfoItemImpostoIPITributado. */
	private String quantidade;

	/**
	 * The econtabil valorUnidadeTributavel for the
	 * NFNotaInfoItemImpostoIPITributado.
	 */
	private String valorUnidadeTributavel;

	/** The econtabil valorTributo for the NFNotaInfoItemImpostoIPITributado. */
	private String valorTributo;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoIPITributado() {
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
	 * Gets the valorBaseCalculo.
	 *
	 * @return the valorBaseCalculo
	 */
	/**
	 * Gets the valorBaseCalculo.
	 *
	 * @return the valorBaseCalculo
	 */
	public String getValorBaseCalculo() {
		return valorBaseCalculo;
	}

	/**
	 * Sets the valorbasecalculo.
	 *
	 * @param id
	 *            the valorbasecalculo to set
	 */
	public void setValorBaseCalculo(String valorbasecalculo) {
		this.valorBaseCalculo = valorbasecalculo;
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
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	/**
	 * Gets the quantidade.
	 *
	 * @return the quantidade
	 */
	public String getQuantidade() {
		return quantidade;
	}

	/**
	 * Sets the quantidade.
	 *
	 * @param id
	 *            the quantidade to set
	 */
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Gets the valorUnidadeTributavel.
	 *
	 * @return the valorUnidadeTributavel
	 */
	/**
	 * Gets the valorUnidadeTributavel.
	 *
	 * @return the valorUnidadeTributavel
	 */
	public String getValorUnidadeTributavel() {
		return valorUnidadeTributavel;
	}

	/**
	 * Sets the valorunidadetributavel.
	 *
	 * @param id
	 *            the valorunidadetributavel to set
	 */
	public void setValorUnidadeTributavel(String valorunidadetributavel) {
		this.valorUnidadeTributavel = valorunidadetributavel;
	}

	/**
	 * Gets the valorTributo.
	 *
	 * @return the valorTributo
	 */
	/**
	 * Gets the valorTributo.
	 *
	 * @return the valorTributo
	 */
	public String getValorTributo() {
		return valorTributo;
	}

	/**
	 * Sets the valortributo.
	 *
	 * @param id
	 *            the valortributo to set
	 */
	public void setValorTributo(String valortributo) {
		this.valorTributo = valortributo;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoIPITributado [getId()=" + getId() + ", getSituacaoTributaria()="
				+ getSituacaoTributaria() + ", getValorBaseCalculo()=" + getValorBaseCalculo()
				+ ", getPercentualAliquota()=" + getPercentualAliquota() + ", getQuantidade()=" + getQuantidade()
				+ ", getValorUnidadeTributavel()=" + getValorUnidadeTributavel() + ", getValorTributo()="
				+ getValorTributo() + ", toString()=" + super.toString() + "]";
	}

}
