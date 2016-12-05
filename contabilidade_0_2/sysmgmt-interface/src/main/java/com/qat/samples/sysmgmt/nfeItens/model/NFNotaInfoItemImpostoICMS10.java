/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS10 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS10. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS10. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS10. */
	private DoisValores situacaoTributaria;

	/** The econtabil modalidadeBCICMS for the NFNotaInfoItemImpostoICMS10. */
	private DoisValores modalidadeBCICMS;

	/** The econtabil valorBaseCalculo for the NFNotaInfoItemImpostoICMS10. */
	private String valorBaseCalculo;

	/** The econtabil percentualAliquota for the NFNotaInfoItemImpostoICMS10. */
	private String percentualAliquota;

	/** The econtabil valorTributo for the NFNotaInfoItemImpostoICMS10. */
	private String valorTributo;

	/** The econtabil modalidadeBCICMSST for the NFNotaInfoItemImpostoICMS10. */
	private DoisValores modalidadeBCICMSST;

	/**
	 * The econtabil percentualMargemValorICMSST for the
	 * NFNotaInfoItemImpostoICMS10.
	 */
	private String percentualMargemValorICMSST;

	/**
	 * The econtabil percentualReducaoBCICMSST for the
	 * NFNotaInfoItemImpostoICMS10.
	 */
	private String percentualReducaoBCICMSST;

	/** The econtabil valorBCICMSST for the NFNotaInfoItemImpostoICMS10. */
	private String valorBCICMSST;

	/**
	 * The econtabil percentualAliquotaImpostoICMSST for the
	 * NFNotaInfoItemImpostoICMS10.
	 */
	private String percentualAliquotaImpostoICMSST;

	/** The econtabil valorICMSST for the NFNotaInfoItemImpostoICMS10. */
	private String valorICMSST;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS10() {
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
	 * Gets the modalidadeBCICMS.
	 *
	 * @return the modalidadeBCICMS
	 */
	/**
	 * Gets the modalidadeBCICMS.
	 *
	 * @return the modalidadeBCICMS
	 */
	public DoisValores getModalidadeBCICMS() {
		return modalidadeBCICMS;
	}

	/**
	 * Sets the modalidadebcicms.
	 *
	 * @param id
	 *            the modalidadebcicms to set
	 */
	public void setModalidadeBCICMS(DoisValores modalidadebcicms) {
		this.modalidadeBCICMS = modalidadebcicms;
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

	/**
	 * Gets the modalidadeBCICMSST.
	 *
	 * @return the modalidadeBCICMSST
	 */
	/**
	 * Gets the modalidadeBCICMSST.
	 *
	 * @return the modalidadeBCICMSST
	 */
	public DoisValores getModalidadeBCICMSST() {
		return modalidadeBCICMSST;
	}

	/**
	 * Sets the modalidadebcicmsst.
	 *
	 * @param id
	 *            the modalidadebcicmsst to set
	 */
	public void setModalidadeBCICMSST(DoisValores modalidadebcicmsst) {
		this.modalidadeBCICMSST = modalidadebcicmsst;
	}

	/**
	 * Gets the percentualMargemValorICMSST.
	 *
	 * @return the percentualMargemValorICMSST
	 */
	/**
	 * Gets the percentualMargemValorICMSST.
	 *
	 * @return the percentualMargemValorICMSST
	 */
	public String getPercentualMargemValorICMSST() {
		return percentualMargemValorICMSST;
	}

	/**
	 * Sets the percentualmargemvaloricmsst.
	 *
	 * @param id
	 *            the percentualmargemvaloricmsst to set
	 */
	public void setPercentualMargemValorICMSST(String percentualmargemvaloricmsst) {
		this.percentualMargemValorICMSST = percentualmargemvaloricmsst;
	}

	/**
	 * Gets the percentualReducaoBCICMSST.
	 *
	 * @return the percentualReducaoBCICMSST
	 */
	/**
	 * Gets the percentualReducaoBCICMSST.
	 *
	 * @return the percentualReducaoBCICMSST
	 */
	public String getPercentualReducaoBCICMSST() {
		return percentualReducaoBCICMSST;
	}

	/**
	 * Sets the percentualreducaobcicmsst.
	 *
	 * @param id
	 *            the percentualreducaobcicmsst to set
	 */
	public void setPercentualReducaoBCICMSST(String percentualreducaobcicmsst) {
		this.percentualReducaoBCICMSST = percentualreducaobcicmsst;
	}

	/**
	 * Gets the valorBCICMSST.
	 *
	 * @return the valorBCICMSST
	 */
	/**
	 * Gets the valorBCICMSST.
	 *
	 * @return the valorBCICMSST
	 */
	public String getValorBCICMSST() {
		return valorBCICMSST;
	}

	/**
	 * Sets the valorbcicmsst.
	 *
	 * @param id
	 *            the valorbcicmsst to set
	 */
	public void setValorBCICMSST(String valorbcicmsst) {
		this.valorBCICMSST = valorbcicmsst;
	}

	/**
	 * Gets the percentualAliquotaImpostoICMSST.
	 *
	 * @return the percentualAliquotaImpostoICMSST
	 */
	/**
	 * Gets the percentualAliquotaImpostoICMSST.
	 *
	 * @return the percentualAliquotaImpostoICMSST
	 */
	public String getPercentualAliquotaImpostoICMSST() {
		return percentualAliquotaImpostoICMSST;
	}

	/**
	 * Sets the percentualaliquotaimpostoicmsst.
	 *
	 * @param id
	 *            the percentualaliquotaimpostoicmsst to set
	 */
	public void setPercentualAliquotaImpostoICMSST(String percentualaliquotaimpostoicmsst) {
		this.percentualAliquotaImpostoICMSST = percentualaliquotaimpostoicmsst;
	}

	/**
	 * Gets the valorICMSST.
	 *
	 * @return the valorICMSST
	 */
	/**
	 * Gets the valorICMSST.
	 *
	 * @return the valorICMSST
	 */
	public String getValorICMSST() {
		return valorICMSST;
	}

	/**
	 * Sets the valoricmsst.
	 *
	 * @param id
	 *            the valoricmsst to set
	 */
	public void setValorICMSST(String valoricmsst) {
		this.valorICMSST = valoricmsst;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMS10 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getModalidadeBCICMS()="
				+ getModalidadeBCICMS() + ", getValorBaseCalculo()=" + getValorBaseCalculo()
				+ ", getPercentualAliquota()=" + getPercentualAliquota() + ", getValorTributo()=" + getValorTributo()
				+ ", getModalidadeBCICMSST()=" + getModalidadeBCICMSST() + ", getPercentualMargemValorICMSST()="
				+ getPercentualMargemValorICMSST() + ", getPercentualReducaoBCICMSST()="
				+ getPercentualReducaoBCICMSST() + ", getValorBCICMSST()=" + getValorBCICMSST()
				+ ", getPercentualAliquotaImpostoICMSST()=" + getPercentualAliquotaImpostoICMSST()
				+ ", getValorICMSST()=" + getValorICMSST() + ", toString()=" + super.toString() + "]";
	}

}
