/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMS30 extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMS30. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMS30. */
	private DoisValores origem;

	/** The econtabil situacaoTributaria for the NFNotaInfoItemImpostoICMS30. */
	private DoisValores situacaoTributaria;

	/** The econtabil modalidadeBCICMSST for the NFNotaInfoItemImpostoICMS30. */
	private DoisValores modalidadeBCICMSST;

	/**
	 * The econtabil percentualMargemValorAdicionadoICMSST for the
	 * NFNotaInfoItemImpostoICMS30.
	 */
	private String percentualMargemValorAdicionadoICMSST;

	/**
	 * The econtabil percentualReducaoBCICMSST for the
	 * NFNotaInfoItemImpostoICMS30.
	 */
	private String percentualReducaoBCICMSST;

	/** The econtabil valorBCICMSST for the NFNotaInfoItemImpostoICMS30. */
	private String valorBCICMSST;

	/**
	 * The econtabil percentualAliquotaImpostoICMSST for the
	 * NFNotaInfoItemImpostoICMS30.
	 */
	private String percentualAliquotaImpostoICMSST;

	/** The econtabil valorImpostoICMSST for the NFNotaInfoItemImpostoICMS30. */
	private String valorImpostoICMSST;

	/**
	 * The econtabil valorICMSDesoneracao for the NFNotaInfoItemImpostoICMS30.
	 */
	private String valorICMSDesoneracao;

	/** The econtabil desoneracao for the NFNotaInfoItemImpostoICMS30. */
	private DoisValores desoneracao;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMS30() {
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
	 * Gets the valorImpostoICMSST.
	 *
	 * @return the valorImpostoICMSST
	 */
	/**
	 * Gets the valorImpostoICMSST.
	 *
	 * @return the valorImpostoICMSST
	 */
	public String getValorImpostoICMSST() {
		return valorImpostoICMSST;
	}

	/**
	 * Sets the valorimpostoicmsst.
	 *
	 * @param id
	 *            the valorimpostoicmsst to set
	 */
	public void setValorImpostoICMSST(String valorimpostoicmsst) {
		this.valorImpostoICMSST = valorimpostoicmsst;
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
		return "NFNotaInfoItemImpostoICMS30 [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getModalidadeBCICMSST()="
				+ getModalidadeBCICMSST() + ", getPercentualMargemValorAdicionadoICMSST()="
				+ getPercentualMargemValorAdicionadoICMSST() + ", getPercentualReducaoBCICMSST()="
				+ getPercentualReducaoBCICMSST() + ", getValorBCICMSST()=" + getValorBCICMSST()
				+ ", getPercentualAliquotaImpostoICMSST()=" + getPercentualAliquotaImpostoICMSST()
				+ ", getValorImpostoICMSST()=" + getValorImpostoICMSST() + ", getValorICMSDesoneracao()="
				+ getValorICMSDesoneracao() + ", getDesoneracao()=" + getDesoneracao() + ", toString()="
				+ super.toString() + "]";
	}

}
