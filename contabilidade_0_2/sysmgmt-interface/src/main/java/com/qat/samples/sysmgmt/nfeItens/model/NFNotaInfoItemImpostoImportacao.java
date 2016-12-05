/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoImportacao extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoImportacao. */
	private Integer id;

	/**
	 * The econtabil valorBaseCalculo for the NFNotaInfoItemImpostoImportacao.
	 */
	private String valorBaseCalculo;

	/**
	 * The econtabil valorDespesaAduaneira for the
	 * NFNotaInfoItemImpostoImportacao.
	 */
	private String valorDespesaAduaneira;

	/**
	 * The econtabil valorImpostoImportacao for the
	 * NFNotaInfoItemImpostoImportacao.
	 */
	private String valorImpostoImportacao;

	/** The econtabil valorIOF for the NFNotaInfoItemImpostoImportacao. */
	private String valorIOF;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoImportacao() {
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
	 * Gets the valorDespesaAduaneira.
	 *
	 * @return the valorDespesaAduaneira
	 */
	/**
	 * Gets the valorDespesaAduaneira.
	 *
	 * @return the valorDespesaAduaneira
	 */
	public String getValorDespesaAduaneira() {
		return valorDespesaAduaneira;
	}

	/**
	 * Sets the valordespesaaduaneira.
	 *
	 * @param id
	 *            the valordespesaaduaneira to set
	 */
	public void setValorDespesaAduaneira(String valordespesaaduaneira) {
		this.valorDespesaAduaneira = valordespesaaduaneira;
	}

	/**
	 * Gets the valorImpostoImportacao.
	 *
	 * @return the valorImpostoImportacao
	 */
	/**
	 * Gets the valorImpostoImportacao.
	 *
	 * @return the valorImpostoImportacao
	 */
	public String getValorImpostoImportacao() {
		return valorImpostoImportacao;
	}

	/**
	 * Sets the valorimpostoimportacao.
	 *
	 * @param id
	 *            the valorimpostoimportacao to set
	 */
	public void setValorImpostoImportacao(String valorimpostoimportacao) {
		this.valorImpostoImportacao = valorimpostoimportacao;
	}

	/**
	 * Gets the valorIOF.
	 *
	 * @return the valorIOF
	 */
	/**
	 * Gets the valorIOF.
	 *
	 * @return the valorIOF
	 */
	public String getValorIOF() {
		return valorIOF;
	}

	/**
	 * Sets the valoriof.
	 *
	 * @param id
	 *            the valoriof to set
	 */
	public void setValorIOF(String valoriof) {
		this.valorIOF = valoriof;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoImportacao [getId()=" + getId() + ", getValorBaseCalculo()="
				+ getValorBaseCalculo() + ", getValorDespesaAduaneira()=" + getValorDespesaAduaneira()
				+ ", getValorImpostoImportacao()=" + getValorImpostoImportacao() + ", getValorIOF()=" + getValorIOF()
				+ ", toString()=" + super.toString() + "]";
	}

}
