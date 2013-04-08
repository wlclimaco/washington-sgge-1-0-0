package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Classcliente.
 */
public class Classcliente extends SensusModel
{

    /** The codemp. */
    private Integer codemp;

    /** The codfilial. */
    private Integer codfilial;

    /** The codclascli. */
    private Integer codclascli;

    /** The descclascli. */
    private String descclascli;

    /** The siglaclascli. */
    private String siglaclascli;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codemp.
	 *
	 * @return the codemp
	 */
	public Integer getCodemp() {
		return codemp;
	}

	/**
	 * Sets the codemp.
	 *
	 * @param codemp the new codemp
	 */
	public void setCodemp(Integer codemp) {
		this.codemp = codemp;
	}

	/**
	 * Gets the codfilial.
	 *
	 * @return the codfilial
	 */
	public Integer getCodfilial() {
		return codfilial;
	}

	/**
	 * Sets the codfilial.
	 *
	 * @param codfilial the new codfilial
	 */
	public void setCodfilial(Integer codfilial) {
		this.codfilial = codfilial;
	}

	/**
	 * Gets the codclascli.
	 *
	 * @return the codclascli
	 */
	public Integer getCodclascli() {
		return codclascli;
	}

	/**
	 * Sets the codclascli.
	 *
	 * @param codclascli the new codclascli
	 */
	public void setCodclascli(Integer codclascli) {
		this.codclascli = codclascli;
	}

	/**
	 * Gets the descclascli.
	 *
	 * @return the descclascli
	 */
	public String getDescclascli() {
		return descclascli;
	}

	/**
	 * Sets the descclascli.
	 *
	 * @param descclascli the new descclascli
	 */
	public void setDescclascli(String descclascli) {
		this.descclascli = descclascli;
	}

	/**
	 * Gets the siglaclascli.
	 *
	 * @return the siglaclascli
	 */
	public String getSiglaclascli() {
		return siglaclascli;
	}

	/**
	 * Sets the siglaclascli.
	 *
	 * @param siglaclascli the new siglaclascli
	 */
	public void setSiglaclascli(String siglaclascli) {
		this.siglaclascli = siglaclascli;
	}

	/**
	 * Gets the listinsalt.
	 *
	 * @return the listinsalt
	 */
	public List<Auditoria> getListinsalt() {
		return listinsalt;
	}

	/**
	 * Sets the listinsalt.
	 *
	 * @param listinsalt the new listinsalt
	 */
	public void setListinsalt(List<Auditoria> listinsalt) {
		this.listinsalt = listinsalt;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Classcliente [codemp=" + codemp + ", codfilial=" + codfilial
				+ ", codclascli=" + codclascli + ", descclascli=" + descclascli
				+ ", siglaclascli=" + siglaclascli + ", listinsalt="
				+ listinsalt + ", getCodemp()=" + getCodemp()
				+ ", getCodfilial()=" + getCodfilial() + ", getCodclascli()="
				+ getCodclascli() + ", getDescclascli()=" + getDescclascli()
				+ ", getSiglaclascli()=" + getSiglaclascli()
				+ ", getListinsalt()=" + getListinsalt() + "]";
	}
}
