/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemProdutoCombustivel extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemProdutoCombustivel. */
	private Integer id;

	/**
	 * The econtabil codigoProdutoANP for the NFNotaInfoItemProdutoCombustivel.
	 */
	private String codigoProdutoANP;

	/**
	 * The econtabil percentualGasNatural for the
	 * NFNotaInfoItemProdutoCombustivel.
	 */
	private String percentualGasNatural;

	/**
	 * The econtabil codigoAutorizacaoCOFIF for the
	 * NFNotaInfoItemProdutoCombustivel.
	 */
	private String codigoAutorizacaoCOFIF;

	/** The econtabil quantidade for the NFNotaInfoItemProdutoCombustivel. */
	private String quantidade;

	/** The econtabil uf for the NFNotaInfoItemProdutoCombustivel. */
	private String uf;

	/** The econtabil cide for the NFNotaInfoItemProdutoCombustivel. */
	private NFNotaInfoItemProdutoCombustivelCIDE cide;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemProdutoCombustivel() {
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
	 * Gets the codigoProdutoANP.
	 *
	 * @return the codigoProdutoANP
	 */
	/**
	 * Gets the codigoProdutoANP.
	 *
	 * @return the codigoProdutoANP
	 */
	public String getCodigoProdutoANP() {
		return codigoProdutoANP;
	}

	/**
	 * Sets the codigoprodutoanp.
	 *
	 * @param id
	 *            the codigoprodutoanp to set
	 */
	public void setCodigoProdutoANP(String codigoprodutoanp) {
		this.codigoProdutoANP = codigoprodutoanp;
	}

	/**
	 * Gets the percentualGasNatural.
	 *
	 * @return the percentualGasNatural
	 */
	/**
	 * Gets the percentualGasNatural.
	 *
	 * @return the percentualGasNatural
	 */
	public String getPercentualGasNatural() {
		return percentualGasNatural;
	}

	/**
	 * Sets the percentualgasnatural.
	 *
	 * @param id
	 *            the percentualgasnatural to set
	 */
	public void setPercentualGasNatural(String percentualgasnatural) {
		this.percentualGasNatural = percentualgasnatural;
	}

	/**
	 * Gets the codigoAutorizacaoCOFIF.
	 *
	 * @return the codigoAutorizacaoCOFIF
	 */
	/**
	 * Gets the codigoAutorizacaoCOFIF.
	 *
	 * @return the codigoAutorizacaoCOFIF
	 */
	public String getCodigoAutorizacaoCOFIF() {
		return codigoAutorizacaoCOFIF;
	}

	/**
	 * Sets the codigoautorizacaocofif.
	 *
	 * @param id
	 *            the codigoautorizacaocofif to set
	 */
	public void setCodigoAutorizacaoCOFIF(String codigoautorizacaocofif) {
		this.codigoAutorizacaoCOFIF = codigoautorizacaocofif;
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
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	/**
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Sets the uf.
	 *
	 * @param id
	 *            the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Gets the cide.
	 *
	 * @return the cide
	 */
	/**
	 * Gets the cide.
	 *
	 * @return the cide
	 */
	public NFNotaInfoItemProdutoCombustivelCIDE getCide() {
		return cide;
	}

	/**
	 * Sets the cide.
	 *
	 * @param id
	 *            the cide to set
	 */
	public void setCide(NFNotaInfoItemProdutoCombustivelCIDE cide) {
		this.cide = cide;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemProdutoCombustivel [getId()=" + getId() + ", getCodigoProdutoANP()="
				+ getCodigoProdutoANP() + ", getPercentualGasNatural()=" + getPercentualGasNatural()
				+ ", getCodigoAutorizacaoCOFIF()=" + getCodigoAutorizacaoCOFIF() + ", getQuantidade()="
				+ getQuantidade() + ", getUf()=" + getUf() + ", toString()=" + super.toString() + "]";
	}

}
