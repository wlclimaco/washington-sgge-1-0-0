/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMSST extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMSST. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMSST. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMSST. */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil valorBCICMSSTRetidoUFRemetente for the
	 * NFNotaInfoItemImpostoICMSST.
	 */
	private String valorBCICMSSTRetidoUFRemetente;

	/**
	 * The econtabil valorICMSSTRetidoUFRemetente for the
	 * NFNotaInfoItemImpostoICMSST.
	 */
	private String valorICMSSTRetidoUFRemetente;

	/**
	 * The econtabil valorBCICMSSTUFDestino for the NFNotaInfoItemImpostoICMSST.
	 */
	private String valorBCICMSSTUFDestino;

	/**
	 * The econtabil valorICMSSTUFDestino for the NFNotaInfoItemImpostoICMSST.
	 */
	private String valorICMSSTUFDestino;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMSST() {
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
	 * Gets the valorBCICMSSTRetidoUFRemetente.
	 *
	 * @return the valorBCICMSSTRetidoUFRemetente
	 */
	/**
	 * Gets the valorBCICMSSTRetidoUFRemetente.
	 *
	 * @return the valorBCICMSSTRetidoUFRemetente
	 */
	public String getValorBCICMSSTRetidoUFRemetente() {
		return valorBCICMSSTRetidoUFRemetente;
	}

	/**
	 * Sets the valorbcicmsstretidoufremetente.
	 *
	 * @param id
	 *            the valorbcicmsstretidoufremetente to set
	 */
	public void setValorBCICMSSTRetidoUFRemetente(String valorbcicmsstretidoufremetente) {
		this.valorBCICMSSTRetidoUFRemetente = valorbcicmsstretidoufremetente;
	}

	/**
	 * Gets the valorICMSSTRetidoUFRemetente.
	 *
	 * @return the valorICMSSTRetidoUFRemetente
	 */
	/**
	 * Gets the valorICMSSTRetidoUFRemetente.
	 *
	 * @return the valorICMSSTRetidoUFRemetente
	 */
	public String getValorICMSSTRetidoUFRemetente() {
		return valorICMSSTRetidoUFRemetente;
	}

	/**
	 * Sets the valoricmsstretidoufremetente.
	 *
	 * @param id
	 *            the valoricmsstretidoufremetente to set
	 */
	public void setValorICMSSTRetidoUFRemetente(String valoricmsstretidoufremetente) {
		this.valorICMSSTRetidoUFRemetente = valoricmsstretidoufremetente;
	}

	/**
	 * Gets the valorBCICMSSTUFDestino.
	 *
	 * @return the valorBCICMSSTUFDestino
	 */
	/**
	 * Gets the valorBCICMSSTUFDestino.
	 *
	 * @return the valorBCICMSSTUFDestino
	 */
	public String getValorBCICMSSTUFDestino() {
		return valorBCICMSSTUFDestino;
	}

	/**
	 * Sets the valorbcicmsstufdestino.
	 *
	 * @param id
	 *            the valorbcicmsstufdestino to set
	 */
	public void setValorBCICMSSTUFDestino(String valorbcicmsstufdestino) {
		this.valorBCICMSSTUFDestino = valorbcicmsstufdestino;
	}

	/**
	 * Gets the valorICMSSTUFDestino.
	 *
	 * @return the valorICMSSTUFDestino
	 */
	/**
	 * Gets the valorICMSSTUFDestino.
	 *
	 * @return the valorICMSSTUFDestino
	 */
	public String getValorICMSSTUFDestino() {
		return valorICMSSTUFDestino;
	}

	/**
	 * Sets the valoricmsstufdestino.
	 *
	 * @param id
	 *            the valoricmsstufdestino to set
	 */
	public void setValorICMSSTUFDestino(String valoricmsstufdestino) {
		this.valorICMSSTUFDestino = valoricmsstufdestino;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMSST [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getValorBCICMSSTRetidoUFRemetente()="
				+ getValorBCICMSSTRetidoUFRemetente() + ", getValorICMSSTRetidoUFRemetente()="
				+ getValorICMSSTRetidoUFRemetente() + ", getValorBCICMSSTUFDestino()=" + getValorBCICMSSTUFDestino()
				+ ", getValorICMSSTUFDestino()=" + getValorICMSSTUFDestino() + ", toString()=" + super.toString() + "]";
	}

}
