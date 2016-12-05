/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImpostoICMSPartilhado extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImpostoICMSPartilhado. */
	private Integer id;

	/** The econtabil origem for the NFNotaInfoItemImpostoICMSPartilhado. */
	private DoisValores origem;

	/**
	 * The econtabil situacaoTributaria for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private DoisValores situacaoTributaria;

	/**
	 * The econtabil modalidadeBCICMS for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private DoisValores modalidadeBCICMS;

	/**
	 * The econtabil valorBCICMS for the NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String valorBCICMS;

	/**
	 * The econtabil percentualReducaoBC for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String percentualReducaoBC;

	/**
	 * The econtabil percentualAliquotaImposto for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String percentualAliquotaImposto;

	/** The econtabil valorICMS for the NFNotaInfoItemImpostoICMSPartilhado. */
	private String valorICMS;

	/**
	 * The econtabil modalidadeBCICMSST for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private DoisValores modalidadeBCICMSST;

	/**
	 * The econtabil percentualMargemValorAdicionadoICMSST for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String percentualMargemValorAdicionadoICMSST;

	/**
	 * The econtabil percentualReducaoBCICMSST for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String percentualReducaoBCICMSST;

	/**
	 * The econtabil valorBCICMSST for the NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String valorBCICMSST;

	/**
	 * The econtabil percentualAliquotaImpostoICMSST for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String percentualAliquotaImpostoICMSST;

	/**
	 * The econtabil valorICMSST for the NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String valorICMSST;

	/**
	 * The econtabil percentualBCOperacaoPropria for the
	 * NFNotaInfoItemImpostoICMSPartilhado.
	 */
	private String percentualBCOperacaoPropria;

	/** The econtabil ufICMSST for the NFNotaInfoItemImpostoICMSPartilhado. */
	private Estado ufICMSST;

	/**
	 * Default constructor.
	 */
	public NFNotaInfoItemImpostoICMSPartilhado() {
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
	 * Gets the valorBCICMS.
	 *
	 * @return the valorBCICMS
	 */
	/**
	 * Gets the valorBCICMS.
	 *
	 * @return the valorBCICMS
	 */
	public String getValorBCICMS() {
		return valorBCICMS;
	}

	/**
	 * Sets the valorbcicms.
	 *
	 * @param id
	 *            the valorbcicms to set
	 */
	public void setValorBCICMS(String valorbcicms) {
		this.valorBCICMS = valorbcicms;
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
	 * Gets the percentualAliquotaImposto.
	 *
	 * @return the percentualAliquotaImposto
	 */
	/**
	 * Gets the percentualAliquotaImposto.
	 *
	 * @return the percentualAliquotaImposto
	 */
	public String getPercentualAliquotaImposto() {
		return percentualAliquotaImposto;
	}

	/**
	 * Sets the percentualaliquotaimposto.
	 *
	 * @param id
	 *            the percentualaliquotaimposto to set
	 */
	public void setPercentualAliquotaImposto(String percentualaliquotaimposto) {
		this.percentualAliquotaImposto = percentualaliquotaimposto;
	}

	/**
	 * Gets the valorICMS.
	 *
	 * @return the valorICMS
	 */
	/**
	 * Gets the valorICMS.
	 *
	 * @return the valorICMS
	 */
	public String getValorICMS() {
		return valorICMS;
	}

	/**
	 * Sets the valoricms.
	 *
	 * @param id
	 *            the valoricms to set
	 */
	public void setValorICMS(String valoricms) {
		this.valorICMS = valoricms;
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
	 * Gets the percentualBCOperacaoPropria.
	 *
	 * @return the percentualBCOperacaoPropria
	 */
	/**
	 * Gets the percentualBCOperacaoPropria.
	 *
	 * @return the percentualBCOperacaoPropria
	 */
	public String getPercentualBCOperacaoPropria() {
		return percentualBCOperacaoPropria;
	}

	/**
	 * Sets the percentualbcoperacaopropria.
	 *
	 * @param id
	 *            the percentualbcoperacaopropria to set
	 */
	public void setPercentualBCOperacaoPropria(String percentualbcoperacaopropria) {
		this.percentualBCOperacaoPropria = percentualbcoperacaopropria;
	}

	/**
	 * Gets the ufICMSST.
	 *
	 * @return the ufICMSST
	 */
	/**
	 * Gets the ufICMSST.
	 *
	 * @return the ufICMSST
	 */
	public Estado getUfICMSST() {
		return ufICMSST;
	}

	/**
	 * Sets the uficmsst.
	 *
	 * @param id
	 *            the uficmsst to set
	 */
	public void setUfICMSST(Estado uficmsst) {
		this.ufICMSST = uficmsst;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImpostoICMSPartilhado [getId()=" + getId() + ", getOrigem()=" + getOrigem()
				+ ", getSituacaoTributaria()=" + getSituacaoTributaria() + ", getModalidadeBCICMS()="
				+ getModalidadeBCICMS() + ", getValorBCICMS()=" + getValorBCICMS() + ", getPercentualReducaoBC()="
				+ getPercentualReducaoBC() + ", getPercentualAliquotaImposto()=" + getPercentualAliquotaImposto()
				+ ", getValorICMS()=" + getValorICMS() + ", getModalidadeBCICMSST()=" + getModalidadeBCICMSST()
				+ ", getPercentualMargemValorAdicionadoICMSST()=" + getPercentualMargemValorAdicionadoICMSST()
				+ ", getPercentualReducaoBCICMSST()=" + getPercentualReducaoBCICMSST() + ", getValorBCICMSST()="
				+ getValorBCICMSST() + ", getPercentualAliquotaImpostoICMSST()=" + getPercentualAliquotaImpostoICMSST()
				+ ", getValorICMSST()=" + getValorICMSST() + ", getPercentualBCOperacaoPropria()="
				+ getPercentualBCOperacaoPropria() + ", getUfICMSST()=" + getUfICMSST() + ", toString()="
				+ super.toString() + "]";
	}

}
