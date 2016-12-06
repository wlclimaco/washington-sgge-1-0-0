/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemDetalheExportacao extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemDetalheExportacao. */
	private Integer id;

	/**
	 * The econtabil atoConcessorioDrawback for the
	 * NFNotaInfoItemDetalheExportacao.
	 */
	private Long atoConcessorioDrawback;

	/**
	 * The econtabil exportacaoIndireta for the NFNotaInfoItemDetalheExportacao.
	 */
	private NFNotaInfoItemExportacaoIndireta exportacaoIndireta;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemDetalheExportacao() {
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
	 * Gets the atoConcessorioDrawback.
	 *
	 * @return the atoConcessorioDrawback
	 */
	/**
	 * Gets the atoConcessorioDrawback.
	 *
	 * @return the atoConcessorioDrawback
	 */
	public Long getAtoConcessorioDrawback() {
		return atoConcessorioDrawback;
	}

	/**
	 * Sets the atoconcessoriodrawback.
	 *
	 * @param id
	 *            the atoconcessoriodrawback to set
	 */
	public void setAtoConcessorioDrawback(Long atoconcessoriodrawback) {
		this.atoConcessorioDrawback = atoconcessoriodrawback;
	}

	/**
	 * Gets the exportacaoIndireta.
	 *
	 * @return the exportacaoIndireta
	 */
	/**
	 * Gets the exportacaoIndireta.
	 *
	 * @return the exportacaoIndireta
	 */
	public NFNotaInfoItemExportacaoIndireta getExportacaoIndireta() {
		return exportacaoIndireta;
	}

	/**
	 * Sets the exportacaoindireta.
	 *
	 * @param id
	 *            the exportacaoindireta to set
	 */
	public void setExportacaoIndireta(NFNotaInfoItemExportacaoIndireta exportacaoindireta) {
		this.exportacaoIndireta = exportacaoindireta;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemDetalheExportacao [getId()=" + getId() + ", getAtoConcessorioDrawback()="
				+ getAtoConcessorioDrawback() + ", toString()=" + super.toString() + "]";
	}

}
