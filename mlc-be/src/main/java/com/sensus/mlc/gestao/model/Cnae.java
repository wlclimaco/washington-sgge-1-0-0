package com.sensus.mlc.gestao.model;

import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Cnae.
 */
public class Cnae extends SensusModel
{

    /** The codcnae. */
    private String codcnae;

    /** The desccnae. */
    private String desccnae;

    /** The seccnae. */
    private String seccnae;

    /** The divcnae. */
    private String divcnae;

    /** The grupcnae. */
    private String grupcnae;

    /** The dtrevcnae. */
    private Date dtrevcnae;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codcnae.
	 *
	 * @return the codcnae
	 */
	public String getCodcnae() {
		return codcnae;
	}

	/**
	 * Sets the codcnae.
	 *
	 * @param codcnae the new codcnae
	 */
	public void setCodcnae(String codcnae) {
		this.codcnae = codcnae;
	}

	/**
	 * Gets the desccnae.
	 *
	 * @return the desccnae
	 */
	public String getDesccnae() {
		return desccnae;
	}

	/**
	 * Sets the desccnae.
	 *
	 * @param desccnae the new desccnae
	 */
	public void setDesccnae(String desccnae) {
		this.desccnae = desccnae;
	}

	/**
	 * Gets the seccnae.
	 *
	 * @return the seccnae
	 */
	public String getSeccnae() {
		return seccnae;
	}

	/**
	 * Sets the seccnae.
	 *
	 * @param seccnae the new seccnae
	 */
	public void setSeccnae(String seccnae) {
		this.seccnae = seccnae;
	}

	/**
	 * Gets the divcnae.
	 *
	 * @return the divcnae
	 */
	public String getDivcnae() {
		return divcnae;
	}

	/**
	 * Sets the divcnae.
	 *
	 * @param divcnae the new divcnae
	 */
	public void setDivcnae(String divcnae) {
		this.divcnae = divcnae;
	}

	/**
	 * Gets the grupcnae.
	 *
	 * @return the grupcnae
	 */
	public String getGrupcnae() {
		return grupcnae;
	}

	/**
	 * Sets the grupcnae.
	 *
	 * @param grupcnae the new grupcnae
	 */
	public void setGrupcnae(String grupcnae) {
		this.grupcnae = grupcnae;
	}

	/**
	 * Gets the dtrevcnae.
	 *
	 * @return the dtrevcnae
	 */
	public Date getDtrevcnae() {
		return dtrevcnae;
	}

	/**
	 * Sets the dtrevcnae.
	 *
	 * @param dtrevcnae the new dtrevcnae
	 */
	public void setDtrevcnae(Date dtrevcnae) {
		this.dtrevcnae = dtrevcnae;
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
		return "Cnae [codcnae=" + codcnae + ", desccnae=" + desccnae
				+ ", seccnae=" + seccnae + ", divcnae=" + divcnae
				+ ", grupcnae=" + grupcnae + ", dtrevcnae=" + dtrevcnae
				+ ", listinsalt=" + listinsalt + ", getCodcnae()="
				+ getCodcnae() + ", getDesccnae()=" + getDesccnae()
				+ ", getSeccnae()=" + getSeccnae() + ", getDivcnae()="
				+ getDivcnae() + ", getGrupcnae()=" + getGrupcnae()
				+ ", getDtrevcnae()=" + getDtrevcnae() + ", getListinsalt()="
				+ getListinsalt() + "]";
	}


}
