/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoCOFINSOutrasOperacoes extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoCOFINSOutrasOperacoes. */
	private Integer id;

	/**
	 * The econtabil situacaoTributaria for the
	 * NFNotaInfoItemImpostoCOFINSOutrasOperacoes.
	 */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil valorBaseCalculo for the
	 * NFNotaInfoItemImpostoCOFINSOutrasOperacoes.
	 */
	private String valorBaseCalculo;

	/**
	 * The econtabil percentualCOFINS for the
	 * NFNotaInfoItemImpostoCOFINSOutrasOperacoes.
	 */
	private String percentualCOFINS;

	/**
	 * The econtabil quantidadeVendida for the
	 * NFNotaInfoItemImpostoCOFINSOutrasOperacoes.
	 */
	private String quantidadeVendida;

	/**
	 * The econtabil valorAliquota for the
	 * NFNotaInfoItemImpostoCOFINSOutrasOperacoes.
	 */
	private String valorAliquota;

	/**
	 * The econtabil valorCOFINS for the
	 * NFNotaInfoItemImpostoCOFINSOutrasOperacoes.
	 */
	private String valorCOFINS;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoCOFINSOutrasOperacoes() {
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
	 * Gets the percentualCOFINS.
	 *
	 * @return the percentualCOFINS
	 */
	/**
	 * Gets the percentualCOFINS.
	 *
	 * @return the percentualCOFINS
	 */
	public String getPercentualCOFINS() {
		return percentualCOFINS;
	}

	/**
	 * Sets the percentualcofins.
	 *
	 * @param id
	 *            the percentualcofins to set
	 */
	public void setPercentualCOFINS(String percentualcofins) {
		this.percentualCOFINS = percentualcofins;
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
		return "NFNotaInfoItemImpostoCOFINSOutrasOperacoes [getId()=" + getId() + ", getSituacaoTributaria()="
				+ getSituacaoTributaria() + ", getValorBaseCalculo()=" + getValorBaseCalculo()
				+ ", getPercentualCOFINS()=" + getPercentualCOFINS() + ", getQuantidadeVendida()="
				+ getQuantidadeVendida() + ", getValorAliquota()=" + getValorAliquota() + ", getValorCOFINS()="
				+ getValorCOFINS() + ", toString()=" + super.toString() + "]";
	}

}
