/** create by system gera-java version 1.0.0 03/12/2016 13:29 : 12*/
package com.qat.samples.sysmgmt.nfeItens.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.).
 * This represents an account for a transfer setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoItemImposto  extends ModelCosmeDamiao {

	/** The econtabil id for the NFNotaInfoItemImposto. */
	private Integer id;

	/** The econtabil valorTotalTributos for the NFNotaInfoItemImposto. */
	private String valorTotalTributos;

	/** The econtabil icms for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoICMS icms;

	/** The econtabil ipi for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoIPI ipi;

	/** The econtabil impostoImportacao for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoImportacao impostoImportacao;

	/** The econtabil issqn for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoISSQN issqn;

	/** The econtabil pis for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoPIS pis;

	/** The econtabil pisst for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoPISST pisst;

	/** The econtabil cofins for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoCOFINS cofins;

	/** The econtabil cofinsst for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoCOFINSST cofinsst;

	/** The econtabil icmsUfDestino for the NFNotaInfoItemImposto. */
	private NFNotaInfoItemImpostoICMSUFDestino icmsUfDestino;


	
	

	public NFNotaInfoItemImposto() {
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
	 * Gets the valorTotalTributos.
	 *
	 * @return the valorTotalTributos
	 */
	/**
	 * Gets the valorTotalTributos.
	 *
	 * @return the valorTotalTributos
	 */
	public String getValorTotalTributos() {
		return valorTotalTributos;
	}

	/**
	 * Sets the valortotaltributos.
	 *
	 * @param id
	 *            the valortotaltributos to set
	 */
	public void setValorTotalTributos(String valortotaltributos) {
		this.valorTotalTributos = valortotaltributos;
	}

	/**
	 * Gets the icms.
	 *
	 * @return the icms
	 */
	/**
	 * Gets the icms.
	 *
	 * @return the icms
	 */
	public NFNotaInfoItemImpostoICMS getIcms() {
		return icms;
	}

	/**
	 * Sets the icms.
	 *
	 * @param id
	 *            the icms to set
	 */
	public void setIcms(NFNotaInfoItemImpostoICMS icms) {
		this.icms = icms;
	}

	/**
	 * Gets the ipi.
	 *
	 * @return the ipi
	 */
	/**
	 * Gets the ipi.
	 *
	 * @return the ipi
	 */
	public NFNotaInfoItemImpostoIPI getIpi() {
		return ipi;
	}

	/**
	 * Sets the ipi.
	 *
	 * @param id
	 *            the ipi to set
	 */
	public void setIpi(NFNotaInfoItemImpostoIPI ipi) {
		this.ipi = ipi;
	}

	/**
	 * Gets the impostoImportacao.
	 *
	 * @return the impostoImportacao
	 */
	/**
	 * Gets the impostoImportacao.
	 *
	 * @return the impostoImportacao
	 */
	public NFNotaInfoItemImpostoImportacao getImpostoImportacao() {
		return impostoImportacao;
	}

	/**
	 * Sets the impostoimportacao.
	 *
	 * @param id
	 *            the impostoimportacao to set
	 */
	public void setImpostoImportacao(NFNotaInfoItemImpostoImportacao impostoimportacao) {
		this.impostoImportacao = impostoimportacao;
	}

	/**
	 * Gets the issqn.
	 *
	 * @return the issqn
	 */
	/**
	 * Gets the issqn.
	 *
	 * @return the issqn
	 */
	public NFNotaInfoItemImpostoISSQN getIssqn() {
		return issqn;
	}

	/**
	 * Sets the issqn.
	 *
	 * @param id
	 *            the issqn to set
	 */
	public void setIssqn(NFNotaInfoItemImpostoISSQN issqn) {
		this.issqn = issqn;
	}

	/**
	 * Gets the pis.
	 *
	 * @return the pis
	 */
	/**
	 * Gets the pis.
	 *
	 * @return the pis
	 */
	public NFNotaInfoItemImpostoPIS getPis() {
		return pis;
	}

	/**
	 * Sets the pis.
	 *
	 * @param id
	 *            the pis to set
	 */
	public void setPis(NFNotaInfoItemImpostoPIS pis) {
		this.pis = pis;
	}

	/**
	 * Gets the pisst.
	 *
	 * @return the pisst
	 */
	/**
	 * Gets the pisst.
	 *
	 * @return the pisst
	 */
	public NFNotaInfoItemImpostoPISST getPisst() {
		return pisst;
	}

	/**
	 * Sets the pisst.
	 *
	 * @param id
	 *            the pisst to set
	 */
	public void setPisst(NFNotaInfoItemImpostoPISST pisst) {
		this.pisst = pisst;
	}

	/**
	 * Gets the cofins.
	 *
	 * @return the cofins
	 */
	/**
	 * Gets the cofins.
	 *
	 * @return the cofins
	 */
	public NFNotaInfoItemImpostoCOFINS getCofins() {
		return cofins;
	}

	/**
	 * Sets the cofins.
	 *
	 * @param id
	 *            the cofins to set
	 */
	public void setCofins(NFNotaInfoItemImpostoCOFINS cofins) {
		this.cofins = cofins;
	}

	/**
	 * Gets the cofinsst.
	 *
	 * @return the cofinsst
	 */
	/**
	 * Gets the cofinsst.
	 *
	 * @return the cofinsst
	 */
	public NFNotaInfoItemImpostoCOFINSST getCofinsst() {
		return cofinsst;
	}

	/**
	 * Sets the cofinsst.
	 *
	 * @param id
	 *            the cofinsst to set
	 */
	public void setCofinsst(NFNotaInfoItemImpostoCOFINSST cofinsst) {
		this.cofinsst = cofinsst;
	}

	/**
	 * Gets the icmsUfDestino.
	 *
	 * @return the icmsUfDestino
	 */
	/**
	 * Gets the icmsUfDestino.
	 *
	 * @return the icmsUfDestino
	 */
	public NFNotaInfoItemImpostoICMSUFDestino getIcmsUfDestino() {
		return icmsUfDestino;
	}

	/**
	 * Sets the icmsufdestino.
	 *
	 * @param id
	 *            the icmsufdestino to set
	 */
	public void setIcmsUfDestino(NFNotaInfoItemImpostoICMSUFDestino icmsufdestino) {
		this.icmsUfDestino = icmsufdestino;
	}

	@Override
	public String toString() {
		return "NFNotaInfoItemImposto [getId()=" + getId() + ", getValorTotalTributos()=" + getValorTotalTributos()
				+ ", getIcms()=" + getIcms() + ", getIpi()=" + getIpi() + ", getImpostoImportacao()="
				+ getImpostoImportacao() + ", getIssqn()=" + getIssqn() + ", getPis()=" + getPis() + ", getPisst()="
				+ getPisst() + ", getCofins()=" + getCofins() + ", getCofinsst()=" + getCofinsst()
				+ ", getIcmsUfDestino()=" + getIcmsUfDestino() + ", toString()=" + super.toString() + "]";
	}

}
