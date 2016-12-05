/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoCOFINSQuantidade extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoCOFINSQuantidade. */
	private Integer id;

	/**
	 * The econtabil situacaoTributaria for the
	 * NFNotaInfoItemImpostoCOFINSQuantidade.
	 */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil quantidadeVendida for the
	 * NFNotaInfoItemImpostoCOFINSQuantidade.
	 */
	private String quantidadeVendida;

	/**
	 * The econtabil valorAliquota for the
	 * NFNotaInfoItemImpostoCOFINSQuantidade.
	 */
	private String valorAliquota;

	/**
	 * The econtabil valorTributo for the NFNotaInfoItemImpostoCOFINSQuantidade.
	 */
	private String valorTributo;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoCOFINSQuantidade() {
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
	 * Gets the quantidadeVendida.
	 *
	 * @return the quantidadeVendida
	 */
	/**
	 * Gets the quantidadeVendida.
	 *
	 * @return the quantidadeVendida
	 */
	public String getQuantidadeVendida() {
		return quantidadeVendida;
	}

	/**
	 * Sets the quantidadevendida.
	 *
	 * @param id
	 *            the quantidadevendida to set
	 */
	public void setQuantidadeVendida(String quantidadevendida) {
		this.quantidadeVendida = quantidadevendida;
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
		return "NFNotaInfoItemImpostoCOFINSQuantidade [getId()=" + getId() + ", getSituacaoTributaria()="
				+ getSituacaoTributaria() + ", getQuantidadeVendida()=" + getQuantidadeVendida()
				+ ", getValorAliquota()=" + getValorAliquota() + ", getValorTributo()=" + getValorTributo()
				+ ", toString()=" + super.toString() + "]";
	}

}
