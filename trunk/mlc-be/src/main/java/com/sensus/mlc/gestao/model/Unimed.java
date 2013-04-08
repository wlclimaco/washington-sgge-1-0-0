package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Unimed.
 */
public class Unimed extends SensusModel
{

    /** The cdunidad. */
    private Integer cdunidad;

    /** The dsunidad. */
    private String dsunidad;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the cdunidad.
	 *
	 * @return the cdunidad
	 */
	public Integer getCdunidad() {
		return cdunidad;
	}

	/**
	 * Sets the cdunidad.
	 *
	 * @param cdunidad the new cdunidad
	 */
	public void setCdunidad(Integer cdunidad) {
		this.cdunidad = cdunidad;
	}

	/**
	 * Gets the dsunidad.
	 *
	 * @return the dsunidad
	 */
	public String getDsunidad() {
		return dsunidad;
	}

	/**
	 * Sets the dsunidad.
	 *
	 * @param dsunidad the new dsunidad
	 */
	public void setDsunidad(String dsunidad) {
		this.dsunidad = dsunidad;
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
		return "Unimed [cdunidad=" + cdunidad + ", dsunidad=" + dsunidad
				+ ", listinsalt=" + listinsalt + ", getCdunidad()="
				+ getCdunidad() + ", getDsunidad()=" + getDsunidad()
				+ ", getListinsalt()=" + getListinsalt() + "]";
	}


}
