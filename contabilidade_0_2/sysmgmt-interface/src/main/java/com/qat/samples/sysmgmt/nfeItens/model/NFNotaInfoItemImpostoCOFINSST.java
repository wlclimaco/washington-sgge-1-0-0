/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoCOFINSST extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoCOFINSST. */
	private Integer id;

	/** The econtabil valorBaseCalculo for the NFNotaInfoItemImpostoCOFINSST. */
	private String valorBaseCalculo;

	/**
	 * The econtabil percentualAliquota for the NFNotaInfoItemImpostoCOFINSST.
	 */
	private String percentualAliquota;

	/**
	 * The econtabil quantidadeVendida for the NFNotaInfoItemImpostoCOFINSST.
	 */
	private String quantidadeVendida;

	/**
	 * The econtabil valorAliquotaCOFINS for the NFNotaInfoItemImpostoCOFINSST.
	 */
	private String valorAliquotaCOFINS;

	/** The econtabil valorCOFINS for the NFNotaInfoItemImpostoCOFINSST. */
	private String valorCOFINS;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoCOFINSST() {
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
	 * Gets the valorAliquotaCOFINS.
	 *
	 * @return the valorAliquotaCOFINS
	 */
	/**
	 * Gets the valorAliquotaCOFINS.
	 *
	 * @return the valorAliquotaCOFINS
	 */
	public String getValorAliquotaCOFINS() {
		return valorAliquotaCOFINS;
	}

	/**
	 * Sets the valoraliquotacofins.
	 *
	 * @param id
	 *            the valoraliquotacofins to set
	 */
	public void setValorAliquotaCOFINS(String valoraliquotacofins) {
		this.valorAliquotaCOFINS = valoraliquotacofins;
	}

	/**
	 * Gets the valorCOFINS.
	 *
	 * @return the valorCOFINS
	 */
	/**
	 * Gets the valorCOFINS.
	 *
	 * @return the valorCOFINS
	 */
	public String getValorCOFINS() {
		return valorCOFINS;
	}

	/**
	 * Sets the valorcofins.
	 *
	 * @param id
	 *            the valorcofins to set
	 */
	public void setValorCOFINS(String valorcofins) {
		this.valorCOFINS = valorcofins;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoCOFINSST [getId()=" + getId() + ", getValorBaseCalculo()=" + getValorBaseCalculo()
				+ ", getPercentualAliquota()=" + getPercentualAliquota() + ", getQuantidadeVendida()="
				+ getQuantidadeVendida() + ", getValorAliquotaCOFINS()=" + getValorAliquotaCOFINS()
				+ ", getValorCOFINS()=" + getValorCOFINS() + ", toString()=" + super.toString() + "]";
	}

}
