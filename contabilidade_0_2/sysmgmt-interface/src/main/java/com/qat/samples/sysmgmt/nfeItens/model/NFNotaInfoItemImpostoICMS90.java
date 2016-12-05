/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS90 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS90. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS90. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS90. */
	private DoisValores situacaoTributaria;

	/** The econtabil modalidadeBCICMS for the NFNotaInfoItemImpostoICMS90. */
	private DoisValores modalidadeBCICMS;

	/** The econtabil valorBC for the NFNotaInfoItemImpostoICMS90. */
	private String valorBC;

	/**
	 * The econtabil percentualReducaoBC for the NFNotaInfoItemImpostoICMS90.
	 */
	private String percentualReducaoBC;

	/** The econtabil percentualAliquota for the NFNotaInfoItemImpostoICMS90. */
	private String percentualAliquota;

	/** The econtabil valorTributo for the NFNotaInfoItemImpostoICMS90. */
	private String valorTributo;

	/** The econtabil modalidadeBCICMSST for the NFNotaInfoItemImpostoICMS90. */
	private String modalidadeBCICMSST;

	/**
	 * The econtabil percentualMargemValorAdicionadoICMSST for the
	 * NFNotaInfoItemImpostoICMS90.
	 */
	private String percentualMargemValorAdicionadoICMSST;

	/**
	 * The econtabil percentualReducaoBCICMSST for the
	 * NFNotaInfoItemImpostoICMS90.
	 */
	private String percentualReducaoBCICMSST;

	/** The econtabil valorBCST for the NFNotaInfoItemImpostoICMS90. */
	private String valorBCST;

	/**
	 * The econtabil percentualAliquotaImpostoICMSST for the
	 * NFNotaInfoItemImpostoICMS90.
	 */
	private String percentualAliquotaImpostoICMSST;

	/** The econtabil valorICMSST for the NFNotaInfoItemImpostoICMS90. */
	private String valorICMSST;

	/**
	 * The econtabil valorICMSDesoneracao for the NFNotaInfoItemImpostoICMS90.
	 */
	private String valorICMSDesoneracao;

	/** The econtabil desoneracao for the NFNotaInfoItemImpostoICMS90. */
	private DoisValores desoneracao;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS90() {
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
	 * Gets the valorBC.
	 *
	 * @return the valorBC
	 */
	/**
	 * Gets the valorBC.
	 *
	 * @return the valorBC
	 */
	public String getValorBC() {
		return valorBC;
	}

	/**
	 * Sets the valorbc.
	 *
	 * @param id
	 *            the valorbc to set
	 */
	public void setValorBC(String valorbc) {
		this.valorBC = valorbc;
	}

	/**
	 * Gets the percentualReducaoBC.
	 *
	 * @return the percentualReducaoBC
	 */
	/**
	 * Gets the percentualReducaoBC.
	 *
	 * @return the percentualReducaoBC
	 */
	public String getPercentualReducaoBC() {
		return percentualReducaoBC;
	}

	/**
	 * Sets the percentualreducaobc.
	 *
	 * @param id
	 *            the percentualreducaobc to set
	 */
	public void setPercentualReducaoBC(String percentualreducaobc) {
		this.percentualReducaoBC = percentualreducaobc;
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
	public String getModalidadeBCICMSST() {
		return modalidadeBCICMSST;
	}

	/**
	 * Sets the modalidadebcicmsst.
	 *
	 * @param id
	 *            the modalidadebcicmsst to set
	 */
	public void setModalidadeBCICMSST(String modalidadebcicmsst) {
		this.modalidadeBCICMSST = modalidadebcicmsst;
	}

	/**
	 * Gets the percentualMargemValorAdicionadoICMSST.
	 *
	 * @return the percentualMargemValorAdicionadoICMSST
	 */
	/**
	 * Gets the percentualMargemValorAdicionadoICMSST.
	 *
	 * @return the percentualMargemValorAdicionadoICMSST
	 */
	public String getPercentualMargemValorAdicionadoICMSST() {
		return percentualMargemValorAdicionadoICMSST;
	}

	/**
	 * Sets the percentualmargemvaloradicionadoicmsst.
	 *
	 * @param id
	 *            the percentualmargemvaloradicionadoicmsst to set
	 */
	public void setPercentualMargemValorAdicionadoICMSST(String percentualmargemvaloradicionadoicmsst) {
		this.percentualMargemValorAdicionadoICMSST = percentualmargemvaloradicionadoicmsst;
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
	 * Gets the valorBCST.
	 *
	 * @return the valorBCST
	 */
	/**
	 * Gets the valorBCST.
	 *
	 * @return the valorBCST
	 */
	public String getValorBCST() {
		return valorBCST;
	}

	/**
	 * Sets the valorbcst.
	 *
	 * @param id
	 *            the valorbcst to set
	 */
	public void setValorBCST(String valorbcst) {
		this.valorBCST = valorbcst;
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

	/**
	 * Gets the valorICMSDesoneracao.
	 *
	 * @return the valorICMSDesoneracao
	 */
	/**
	 * Gets the valorICMSDesoneracao.
	 *
	 * @return the valorICMSDesoneracao
	 */
	public String getValorICMSDesoneracao() {
		return valorICMSDesoneracao;
	}

	/**
	 * Sets the valoricmsdesoneracao.
	 *
	 * @param id
	 *            the valoricmsdesoneracao to set
	 */
	public void setValorICMSDesoneracao(String valoricmsdesoneracao) {
		this.valorICMSDesoneracao = valoricmsdesoneracao;
	}

	/**
	 * Gets the desoneracao.
	 *
	 * @return the desoneracao
	 */
	/**
	 * Gets the desoneracao.
	 *
	 * @return the desoneracao
	 */
	public DoisValores getDesoneracao() {
		return desoneracao;
	}

	/**
	 * Sets the desoneracao.
	 *
	 * @param id
	 *            the desoneracao to set
	 */
	public void setDesoneracao(DoisValores desoneracao) {
		this.desoneracao = desoneracao;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMS90 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getModalidadeBCICMS()="
				+ getModalidadeBCICMS() + ", getValorBC()=" + getValorBC() + ", getPercentualReducaoBC()="
				+ getPercentualReducaoBC() + ", getPercentualAliquota()=" + getPercentualAliquota()
				+ ", getValorTributo()=" + getValorTributo() + ", getModalidadeBCICMSST()=" + getModalidadeBCICMSST()
				+ ", getPercentualMargemValorAdicionadoICMSST()=" + getPercentualMargemValorAdicionadoICMSST()
				+ ", getPercentualReducaoBCICMSST()=" + getPercentualReducaoBCICMSST() + ", getValorBCST()="
				+ getValorBCST() + ", getPercentualAliquotaImpostoICMSST()=" + getPercentualAliquotaImpostoICMSST()
				+ ", getValorICMSST()=" + getValorICMSST() + ", getValorICMSDesoneracao()=" + getValorICMSDesoneracao()
				+ ", getDesoneracao()=" + getDesoneracao() + ", toString()=" + super.toString() + "]";
	}

}
